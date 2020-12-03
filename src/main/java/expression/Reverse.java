package expression;

public class Reverse extends UnaryOperation {
    public Reverse(final CommonExpression operand) {
        super(operand);
    }

    @Override
    public int apply(int value) {
        int result = 0;

        while (0 != value) {
            result *= 10;
            result += value % 10;
            value /= 10;
        }

        return result;
    }

    @Override
    public double apply(final double value) {
        throw new UnsupportedOperationException("Cannot apply reverse to a double value");
    }

    @Override
    public String getOperatorSign() {
        return "reverse";
    }
}
