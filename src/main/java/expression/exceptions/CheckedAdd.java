package expression.exceptions;

import expression.CommonExpression;
import expression.binary.Add;

public class CheckedAdd extends Add {
    public CheckedAdd(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        final int c = super.apply(a, b);

        if (a > 0 && b > 0 && c < 0 || a < 0 && b < 0 && c >= 0) {
            throw new OverflowException(String.format("Overflow to represent the sum of %d and %d", a, b));
        }

        return c;
    }
}
