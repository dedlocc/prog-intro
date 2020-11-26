package expression;

public final class Add extends BinaryOperator {
    public Add(final Expression first, final Expression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        return a + b;
    }

    @Override
    public String getOperatorSign() {
        return "+";
    }
}
