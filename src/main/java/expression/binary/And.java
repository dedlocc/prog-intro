package expression.binary;

import expression.CommonExpression;
import expression.Precedence;

public class And extends BinaryOperation {
    public And(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        return a & b;
    }

    @Override
    public String getOperatorSign() {
        return "&";
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.AND;
    }
}