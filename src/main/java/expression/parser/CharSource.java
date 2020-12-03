package expression.parser;

public interface CharSource {
    char END = '\uffff';

    char next();

    char current();

    char peek();

    int position();

    void reset(final int position);

    default boolean test(final CharMatcher matcher, final boolean next) {
        if (matcher.match(peek())) {
            if (next) {
                next();
            }
            return true;
        }

        return false;
    }

    default boolean test(final CharMatcher matcher) {
        return test(matcher, true);
    }

    default boolean test(final char c) {
        return test(CharMatcher.equals(c));
    }

    default boolean test(final CharSequence s) {
        final var position = position();

        for (var i = 0; i < s.length(); ++i) {
            if (!test(s.charAt(i))) {
                reset(position);
                return false;
            }
        }

        return true;
    }

    @FunctionalInterface
    interface CharMatcher {
        boolean match(final char c);

        static CharMatcher equals(final char ch) {
            return c -> c == ch;
        }
    }
}
