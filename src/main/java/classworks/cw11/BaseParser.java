package classworks.cw11;

public abstract class BaseParser {
    protected final CharSource source;

    protected static final char EOF = 0;
    protected char ch;

    protected BaseParser(final CharSource source) {
        this.source = source;
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.nextChar() : EOF;
    }

    protected boolean test(final char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }

        return false;
    }

    protected void expect(final char expected) {
        if (!test(expected)) {
            throw error(String.format("Expected: '%c', found '%c'", expected, ch));
        }
    }

    protected void expect(final String expected) {
        for (final char c : expected.toCharArray()) {
            expect(c);
        }
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }
}
