package classworks.cw11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public class JsonTest {
    private static void valid(final Object expected, final String json) {
        Assertions.assertEquals(expected, JsonParser.parse(json));
    }

    private static void invalid(final String json) {
        final var e = Assertions.assertThrows(IllegalArgumentException.class, () -> JsonParser.parse(json));
        System.out.printf("Expected exception: %s (%s)%n", e.getClass().getSimpleName(), e.getMessage());
    }

    @Test
    public void testString() {
        valid("", "\"\"");
        valid(" ", "\" \"");
        valid("hello", "\"hello\"");
        valid("hello", "   \"hello\"");
        valid("hello", "\"hello\"   ");
        valid("hello", "  \r\t\n\"hello\"  \r\t\n");
        invalid("\"hello\"  \"world\"");

        valid("true", "\"true\"");
        valid("false", "\"false\"");
        valid("null", "\"null\"");
    }

    @Test
    public void testEscape() {
        valid("\\/\"\b\f\r\n\t", " \"\\\\\\/\\\"\\b\\f\\r\\n\\t\" ");
        invalid("\"\\'\"");
        invalid("\"\\hello\"");
    }

    @Test
    public void testInteger() {
        valid(0, "0");
        valid(0, "-0");
        valid(123, "123");
        valid(-123, "-123");
        valid(-123, "   -123   ");

        invalid("0123");
        invalid("-0123");
        invalid("-00");
        invalid("-");
        invalid("12345678900");
    }

    @Test
    public void testConstant() {
        valid(null, "null");
        valid(true, "true");
        valid(false, "false");
        valid(false, "\tfalse\r");
        invalid("truefalse");
    }

    @Test
    public void testArray() {
        valid(Collections.emptyList(), "[]");
        valid(Collections.emptyList(), " [ ] ");
        valid(List.of(123), " [ 123 ] ");

        valid(List.of(123, 456), "[  \r\t\n123,  \r\t\n456  \r\t\n]");
        valid(List.of(123, "hello"), "[123,\"hello\"]");
        valid(List.of(123, "hello"), " [ 123, \"hello\" ] ");
        valid(List.of(123, true), "[ 123, true ]");
        valid(new ArrayList<>() {
            {
                add(123);
                add(null);
            }
        }, "[123, null]");

        valid(List.of(List.of()), "[[]]");
        valid(List.of(List.of()), " [ [ ] ] ");
        valid(List.of(List.of(123, true)), "[[ 123, true ]]");
        valid(List.of(List.of(123), true), "[[123], true]");
        valid(List.of(List.of(List.of(123)), true), "[ [[123]], true ]");
        valid(List.of(List.of(List.of(123)), List.of(true)), "[[[123]],[true]]");

        invalid("[123, hello]");
        invalid("[123, \"hello\"");
        invalid("[123 \"hello\"]");
        invalid("[123,]");
        invalid("[123, ]");
    }

    @Test
    public void testObject() {
        valid(Collections.emptyMap(), "{}");
        valid(Collections.emptyMap(), " { } ");
        valid(Map.of("hello", "world"), "{\"hello\": \"world\"}");
        valid(Map.of("hello", "world"), "{ \"hello\" :\"world\" }");
        valid(Map.of("hello", 123), "{\"hello\": 123}");
        valid(Map.of("123", "world"), "{\"123\": \"world\"}");
        valid(Map.of("", "world"), "{\"\": \"world\"}");

        valid(Map.of("hello", 123, "world", true), "{\"hello\": 123, \"world\": true}");
        valid(Map.of("hello", Map.of("world", 123)), "{\"hello\": {\"world\": 123}}");
        valid(
            Map.of("a", Map.of("b", "c"), "d", Map.of("e", "f", "g", "h")),
            "{\"a\": {\"b\": \"c\"}, \"d\": {\"e\": \"f\", \"g\": \"h\"}}"
        );

        invalid("{123: \"world\"}");
        invalid("{\"hello\" 123}");
        invalid("{\"hello\": 123");
        invalid("{\"hello\": 123, }");
    }
}
