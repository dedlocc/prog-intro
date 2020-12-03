package expression.unary;

import expression.CommonExpression;
import expression.OverflowException;

public final class Negate extends UnaryOperation {
    public Negate(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        if (Integer.MIN_VALUE == value) {
            throw new OverflowException(
                String.format("Negated value of %d does not fit in integer type", Integer.MIN_VALUE));
        }

        return -value;
    }

    @Override
    public double apply(final double value) {
        return -value;
    }

    @Override
    public String getOperatorSign() {
        return "-";
    }
}
