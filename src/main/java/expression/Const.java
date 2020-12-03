package expression;

public final class Const implements CommonExpression {
    private final Number value;

    public Const(final Number value) {
        this.value = value;
    }

    @Override
    public int evaluate(final int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return value.intValue();
    }

    @Override
    public double evaluate(final double x) {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (null == other || getClass() != other.getClass()) {
            return false;
        }

        return value.equals(((Const) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
