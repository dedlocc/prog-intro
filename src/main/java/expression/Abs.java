package expression;

public class Abs extends UnaryOperation {
    public Abs(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        return Math.abs(value);
    }

    @Override
    public double apply(final double value) {
        return Math.abs(value);
    }

    @Override
    public String getOperatorSign() {
        return "abs";
    }
}
