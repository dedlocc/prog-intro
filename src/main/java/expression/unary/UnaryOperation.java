package expression.unary;

import expression.CommonExpression;
import expression.binary.BinaryOperation;
import expression.exceptions.DoubleNotSupportedException;

public abstract class UnaryOperation implements CommonExpression {
    private final CommonExpression operand;

    public UnaryOperation(final CommonExpression operand) {
        this.operand = operand;
    }

    public abstract int apply(final int value);

    public double apply(final double value) {
        throw new DoubleNotSupportedException("Doubles are not supported by " + getClass().getName());
    }

    public abstract String getOperatorSign();

    @Override
    public int evaluate(final int x) {
        return apply(operand.evaluate(x));
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return apply(operand.evaluate(x, y, z));
    }

    @Override
    public double evaluate(final double x) {
        return apply(operand.evaluate(x));
    }

    @Override
    public String toString() {
        return String.format("(%s %s)", getOperatorSign(), operand);
    }

    @Override
    public String toMiniString() {
        return String.format(
            operand instanceof BinaryOperation ? "%s (%s)" : "%s %s", getOperatorSign(), operand.toMiniString());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (null == other || getClass() != other.getClass()) {
            return false;
        }

        final var o = (UnaryOperation) other;
        return operand.equals(o.operand);
    }

    @Override
    public int hashCode() {
        return 31 * (31 * (operand.hashCode()) + getClass().hashCode());
    }
}