package expression;

public class Square extends UnaryOperation {
    public Square(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(final int value) {
        return value * value;
    }

    @Override
    public double apply(final double value) {
        return value * value;
    }

    @Override
    public String getOperatorSign() {
        return "square";
    }
}
