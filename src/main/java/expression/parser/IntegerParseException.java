package expression.parser;

public class IntegerParseException extends ParseException {
    public IntegerParseException(final String message) {
        super(message);
    }

    public IntegerParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IntegerParseException(final Throwable cause) {
        super(cause);
    }
}
