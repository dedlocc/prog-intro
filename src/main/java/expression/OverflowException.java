package expression;

public class OverflowException extends ExpressionException {
    public OverflowException(final String message) {
        super(message, "overflow");
    }
}
