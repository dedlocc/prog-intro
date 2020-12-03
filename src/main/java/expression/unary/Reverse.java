package expression.unary;

import expression.CommonExpression;
import expression.DoubleNotSupportedException;
import expression.OverflowException;

public final class Reverse extends UnaryOperation {
    public Reverse(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(int value) {
        final int limit = value < 0 ? Integer.MIN_VALUE : -Integer.MAX_VALUE;
        final int secondLimit = limit / 10;

        int result = 0;

        while (0 != value) {
            final var digit = Math.abs(value % 10);

            if (result < secondLimit || (result *= 10) < limit + digit) {
                throw new OverflowException(String.format("Reversed value of %d does not fit in integer type", value));
            }

            result -= digit;
            value /= 10;
        }

        return result;
    }

    @Override
    public double apply(final double value) {
        throw new DoubleNotSupportedException("Cannot apply 'reverse' to a double value");
    }

    @Override
    public String getOperatorSign() {
        return "reverse";
    }
}
