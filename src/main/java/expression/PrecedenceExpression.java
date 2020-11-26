package expression;

public interface PrecedenceExpression extends Expression {
    default Precedence getPrecedence() {
        return Precedence.NORMAL;
    }

    default boolean hasLowerPrecedence(final Expression expr, final boolean right) {
        if (!(expr instanceof PrecedenceExpression)) {
            return false;
        }

        final var comp = getPrecedence().compareTo(((PrecedenceExpression) expr).getPrecedence());
        return 0 < comp || right && 0 == comp && addBracesOnSameRightPrecedence(expr);
    }

    default boolean addBracesOnSameRightPrecedence(final Expression expr) {
        return false;
    }
}
