package expression.exceptions;

import expression.CommonExpression;
import expression.binary.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        if (0 == b) {
            throw new DivisionByZeroException();
        }

        if (Integer.MIN_VALUE == a && -1 == b) {
            throw new OverflowException(String.format("Overflow to represent the quotient of %d and %d", a, b));
        }

        return super.apply(a, b);
    }
}
