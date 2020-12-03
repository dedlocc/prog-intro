package expression;

public class ExpressionException extends RuntimeException {
    private final String reason;

    public ExpressionException(final String message, final String reason) {
        super(message);
        this.reason = reason;
    }

    public ExpressionException(final String message) {
        this(message, "unknown error");
    }

    public String getReason() {
        return reason;
    }
}
