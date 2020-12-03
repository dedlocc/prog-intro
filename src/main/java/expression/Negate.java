package expression;

public class Negate extends UnaryOperation {
    public Negate(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        return -value;
    }

    @Override
    public double apply(final double value) {
        return -value;
    }

    @Override
    public String getOperatorSign() {
        return "-";
    }
}
