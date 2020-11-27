package expression;

public final class Const implements CommonExpression {
    private final double value;
    private final boolean isInt;

    public Const(final int value) {
        this.value = value;
        this.isInt = true;
    }

    public Const(final double value) {
        this.value = value;
        this.isInt = false;
    }

    @Override
    public int evaluate(final int x) {
        return (int) value;
    }

    @Override
    public double evaluate(final double x) {
        return value;
    }

    @Override
    public String toString() {
        return isInt ? Integer.toString((int) value) : Double.toString(value);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (null == other || getClass() != other.getClass()) {
            return false;
        }

        final var o = (Const) other;
        return value == o.value && isInt == o.isInt;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(value) + Boolean.hashCode(isInt);
    }
}
