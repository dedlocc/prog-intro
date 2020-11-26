package expression;

public final class Divide extends BinaryOperator {
    public Divide(final Expression first, final Expression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        return a / b;
    }

    @Override
    public String getOperatorSign() {
        return "/";
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.HIGH;
    }

    @Override
    public boolean addBracesOnSameRightPrecedence(final Expression expr) {
        return true;
    }
}
