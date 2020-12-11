package expression.exceptions;

import expression.CommonExpression;
import expression.unary.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        if (Integer.MIN_VALUE == value) {
            throw new OverflowException(String.format("Overflow to represent negated value of %d", value));
        }

        return super.apply(value);
    }
}
