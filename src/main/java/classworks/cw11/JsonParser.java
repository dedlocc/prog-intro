package classworks.cw11;

import java.util.*;

public class JsonParser extends BaseParser {
    private static final char[] ESCAPES = {
        '"', '"',
        '\\', '\\',
        '/', '/',
        'b', '\b',
        'f', '\f',
        'n', '\n',
        'r', '\r',
        't', '\t',
    };

    public JsonParser(final CharSource source) {
        super(source);
    }

    public static Object parse(final String json) {
        return new JsonParser(new StringSource(json)).parseJson();
    }

    protected Object parseJson() {
        final var result = parseElement();
        if (!test(EOF)) {
            throw error("End-of-input expected");
        }
        return result;
    }

    private Object parseElement() {
        skipWhitespace();
        final var value = parseValue();
        skipWhitespace();
        return value;
    }

    private Object parseValue() {
        if (test('"')) {
            return parseString();
        }
        if (test('[')) {
            return parseArray();
        }
        if (test('{')) {
            return parseObject();
        }
        if (test('t')) {
            expect("rue");
            return true;
        }
        if (test('f')) {
            expect("alse");
            return false;
        }
        if (test('n')) {
            expect("ull");
            return null;
        }

        return parseNumber();
    }

    private String parseString() {
        final var value = new StringBuilder();
        outer:
        while (!test('"')) {
            if (test('\\')) {
                for (var i = 0; i < ESCAPES.length; ++i) {
                    if (test(ESCAPES[i++])) {
                        value.append(ESCAPES[i]);
                        continue outer;
                    }
                }
                throw error(String.format("Invalid escape character: '\\%c'", ch));
            }

            value.append(ch);
            nextChar();
        }
        return value.toString();
    }

    private List<Object> parseArray() {
        skipWhitespace();
        if (test(']')) {
            return Collections.emptyList();
        }

        final List<Object> result = new ArrayList<>();
        do {
            result.add(parseElement());
        } while (test(','));

        expect(']');

        return result;
    }

    private Map<String, Object> parseObject() {
        skipWhitespace();
        if (test('}')) {
            return Collections.emptyMap();
        }

        final Map<String, Object> result = new HashMap<>();
        do {
            skipWhitespace();
            expect('"');
            final var key = parseString();
            skipWhitespace();
            expect(':');
            result.put(key, parseElement());
        } while (test(','));

        expect('}');

        return result;
    }

    private Number parseNumber() {
        final var sb = new StringBuilder();
        if (test('-')) {
            sb.append('-');
        }
        if (test('0')) {
            return 0;
        }
        while ('0' <= ch && '9' >= ch) {
            sb.append(ch);
            nextChar();
        }

        try {
            return Integer.parseInt(sb.toString());
        } catch (final NumberFormatException e) {
            throw error(String.format("Expected int, found %s: %s", sb, e.getMessage()));
        }
    }

    private void skipWhitespace() {
        //noinspection StatementWithEmptyBody
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
        }
    }
}
