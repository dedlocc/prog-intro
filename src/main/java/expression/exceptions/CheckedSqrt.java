package expression.exceptions;

import expression.CommonExpression;
import expression.unary.UnaryOperation;

public class CheckedSqrt extends UnaryOperation {
    public CheckedSqrt(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        if (0 > value) {
            throw new OverflowException(String.format("Overflow to represent absolute value of %d", value));
        }

        return (int) Math.sqrt(value);
    }

    @Override
    public String getOperatorSign() {
        return "sqrt";
    }
}
