package expression.unary;

import expression.CommonExpression;
import expression.DoubleNotSupportedException;

public class Not extends UnaryOperation {
    public Not(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        return ~value;
    }

    @Override
    public double apply(final double value) {
        throw new DoubleNotSupportedException("Cannot apply bitwise not to double values");
    }

    @Override
    public String getOperatorSign() {
        return "~";
    }
}
