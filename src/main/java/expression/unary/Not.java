package expression.unary;

import expression.CommonExpression;

public class Not extends UnaryOperation {
    public Not(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        return ~value;
    }

    @Override
    public String getOperatorSign() {
        return "~";
    }
}
