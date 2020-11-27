package expression;

public final class Subtract extends BinaryOperator {
    public Subtract(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        return a - b;
    }

    @Override
    public double apply(final double a, final double b) {
        return a - b;
    }

    @Override
    public String getOperatorSign() {
        return "-";
    }

    @Override
    public boolean addBracesOnSameRightPrecedence(final Expression expr) {
        return true;
    }
}
