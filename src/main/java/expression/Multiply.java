package expression;

public final class Multiply extends BinaryOperation {
    public Multiply(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        return a * b;
    }

    @Override
    public double apply(final double a, final double b) {
        return a * b;
    }

    @Override
    public String getOperatorSign() {
        return "*";
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.MULTIPLY;
    }

    @Override
    public boolean lowerIfSame(final PrecedenceAware expr) {
        return getClass() != expr.getClass();
    }
}
