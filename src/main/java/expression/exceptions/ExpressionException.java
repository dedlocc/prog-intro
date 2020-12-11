package expression.exceptions;

public class ExpressionException extends RuntimeException {
    protected static final String DEFAULT_REASON = "Unknown error";
    private final String reason;

    public ExpressionException(final String message, final String reason) {
        super(message);
        this.reason = reason;
    }

    public ExpressionException(final String message) {
        this(message, DEFAULT_REASON);
    }

    public ExpressionException(final String message, final String reason, final Throwable cause) {
        super(message, cause);
        this.reason = reason;
    }

    public ExpressionException(final String message, final Throwable cause) {
        this(message, DEFAULT_REASON, cause);
    }

    public String getReason() {
        return reason;
    }
}
