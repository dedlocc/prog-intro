package expression.exceptions;

import expression.CommonExpression;
import expression.binary.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        final int c = super.apply(a, b);

        if (Integer.MIN_VALUE == a && -1 == b || 0 != b && a != c / b) {
            throw new OverflowException(String.format("Overflow to represent the product of %d and %d", a, b));
        }

        return c;
    }
}
