package expression;

public abstract class BinaryOperation implements CommonExpression, PrecedenceAware {
    private final CommonExpression first;
    private final CommonExpression second;

    public BinaryOperation(final CommonExpression first, final CommonExpression second) {
        this.first = first;
        this.second = second;
    }

    public abstract int apply(final int a, final int b);

    public abstract double apply(final double a, final double b);

    public abstract String getOperatorSign();

    @Override
    public int evaluate(final int x) {
        return apply(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return apply(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public double evaluate(final double x) {
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

    private String formatChild(final CommonExpression child, final boolean right) {
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

        final var o = (BinaryOperation) other;
        return first.equals(o.first) && second.equals(o.second);
    }

    @Override
    public int hashCode() {
        return 31 * (31 * (31 * first.hashCode() + second.hashCode()) + getClass().hashCode());
    }
}
