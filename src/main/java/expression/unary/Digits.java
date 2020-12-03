package expression.unary;

import expression.CommonExpression;
import expression.DoubleNotSupportedException;

public final class Digits extends UnaryOperation {
    public Digits(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(int value) {
        int result = 0;

        while (0 != value) {
            result += value % 10;
            value /= 10;
        }

        return Math.abs(result);
    }

    @Override
    public double apply(final double value) {
        throw new DoubleNotSupportedException("Cannot apply 'digits' to a double value");
    }

    @Override
    public String getOperatorSign() {
        return "digits";
    }
}
