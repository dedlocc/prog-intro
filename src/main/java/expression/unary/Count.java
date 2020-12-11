package expression.unary;

import expression.CommonExpression;

public class Count extends UnaryOperation {
    public Count(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        return Integer.bitCount(value);
    }

    @Override
    public String getOperatorSign() {
        return "count";
    }
}
