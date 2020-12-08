package expression.unary;

import expression.CommonExpression;
import expression.DoubleNotSupportedException;

public class Count extends UnaryOperation {
    public Count(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        return Integer.bitCount(value);
    }

    @Override
    public double apply(final double value) {
        throw new DoubleNotSupportedException("Cannot count set bits of double values");
    }

    @Override
    public String getOperatorSign() {
        return "count";
    }
}
