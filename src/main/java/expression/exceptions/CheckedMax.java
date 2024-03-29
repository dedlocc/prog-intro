package expression.exceptions;

import expression.CommonExpression;
import expression.Precedence;
import expression.binary.BinaryOperation;

public class CheckedMax extends BinaryOperation {
    public CheckedMax(final CommonExpression first, final CommonExpression second) {
        super(first, second);
    }

    @Override
    public int apply(final int a, final int b) {
        return a < b ? b : a;
    }

    @Override
    public String getOperatorSign() {
        return "max";
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.MIN_MAX;
    }
}
