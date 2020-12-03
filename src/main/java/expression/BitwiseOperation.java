package expression;

public abstract class BitwiseOperation extends BinaryOperation {
    public BitwiseOperation(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public double apply(final double a, final double b) {
        throw new UnsupportedOperationException("Cannot apply bitwise operation to double values");
    }
}
