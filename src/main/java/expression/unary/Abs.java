package expression.unary;

import expression.CommonExpression;
import expression.OverflowException;

public final class Abs extends UnaryOperation {
    public Abs(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        if (Integer.MIN_VALUE == value) {
            throw new OverflowException(
                String.format("Absolute value of %d does not fit in integer type", Integer.MIN_VALUE));
        }

        return Math.abs(value);
    }

    @Override
    public double apply(final double value) {
        return Math.abs(value);
    }

    @Override
    public String getOperatorSign() {
        return "abs";
    }
}
