package expression.binary;

import expression.CommonExpression;
import expression.DoubleNotSupportedException;

public abstract class BitwiseOperation extends BinaryOperation {
    public BitwiseOperation(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public double apply(final double a, final double b) {
        throw new DoubleNotSupportedException("Cannot apply bitwise operation to double values");
    }
}
