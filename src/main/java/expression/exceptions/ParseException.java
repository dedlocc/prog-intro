package expression.exceptions;

public class ParseException extends ExpressionException {
    private final int pos;

    public ParseException(final String message, final String reason, final int pos) {
        super(formatMessage(message, pos), reason);
        this.pos = pos;
    }

    public ParseException(final String message, final String reason, final int pos, final Throwable cause) {
        super(formatMessage(message, pos), reason, cause);
        this.pos = pos;
    }

    private static String formatMessage(final String message, final int pos) {
        return message + " at position " + (1 + pos);
    }

    public int getPos() {
        return pos;
    }
}
