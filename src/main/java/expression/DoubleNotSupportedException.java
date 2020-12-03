package expression;

public class DoubleNotSupportedException extends ExpressionException {
    public DoubleNotSupportedException(final String message) {
        super(message, "double not supported");
    }
}
