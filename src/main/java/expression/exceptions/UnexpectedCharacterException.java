package expression.exceptions;

import expression.parser.CharSource;

public class UnexpectedCharacterException extends ParseException {
    public UnexpectedCharacterException(final String message, final String reason, final int pos) {
        super(message, reason, pos);
    }

    public static UnexpectedCharacterException fromCharSource(final CharSource chars) {
        final var ch = chars.peek();

        if (CharSource.END == ch) {
            return new UnexpectedCharacterException(
                "Unexpected end of string", "unexpected end of string", chars.position());
        }

        return new UnexpectedCharacterException(
            String.format("Unexpected '%c'", ch), "unexpected character", chars.position());
    }
}
