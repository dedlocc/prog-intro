package expression.binary;

import expression.CommonExpression;
import expression.Precedence;

public final class LeftShift extends BitwiseOperation {
    public LeftShift(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        return a << b;
    }

    @Override
    public String getOperatorSign() {
        return "<<";
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.SHIFT;
    }
}
