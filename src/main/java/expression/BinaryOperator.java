package expression;

public abstract class BinaryOperator implements PrecedenceExpression {
    private final Expression first;
    private final Expression second;

    public BinaryOperator(final Expression first, final Expression second) {
        this.first = first;
        this.second = second;
    }

    public abstract int apply(final int a, final int b);

    public abstract String getOperatorSign();

    @Override
    public int evaluate(final int x) {
        return apply(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", first, getOperatorSign(), second);
    }

    @Override
    public String toMiniString() {
        return String.format("%s %s %s", formatChild(first, false), getOperatorSign(), formatChild(second, true));
    }

    private String formatChild(final Expression child, final boolean right) {
        final String miniString = child.toMiniString();
        return hasLowerPrecedence(child, right) ? String.format("(%s)", miniString) : miniString;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (null == other || getClass() != other.getClass()) {
            return false;
        }

        final var o = (BinaryOperator) other;
        return first.equals(o.first) && second.equals(o.second);
    }

    @Override
    public int hashCode() {
        return 31 * (31 * (31 * first.hashCode() + second.hashCode()) + getClass().hashCode());
    }
}
