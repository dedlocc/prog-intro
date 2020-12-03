package expression.parser;

public class VariableParseException extends ParseException {
    public VariableParseException(final String message) {
        super(message);
    }

    public VariableParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public VariableParseException(final Throwable cause) {
        super(cause);
    }
}
