package expression.exceptions;

public class EvaluationException extends ExpressionException {
    public EvaluationException(final String message, final String reason) {
        super(message, reason);
    }

    public EvaluationException(final String message) {
        super(message);
    }
}
