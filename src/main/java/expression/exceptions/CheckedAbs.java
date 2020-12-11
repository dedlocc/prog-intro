package expression.exceptions;

import expression.CommonExpression;
import expression.unary.UnaryOperation;

public class CheckedAbs extends UnaryOperation {
    public CheckedAbs(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        if (Integer.MIN_VALUE == value) {
            throw new OverflowException(String.format("Overflow to represent absolute value of %d", value));
        }

        return value < 0 ? -value : value;
    }

    @Override
    public String getOperatorSign() {
        return "abs";
    }
}
