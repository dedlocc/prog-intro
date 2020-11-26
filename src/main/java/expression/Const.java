package expression;

public final class Const implements Expression {
    private final int value;

    public Const(final int value) {
        this.value = value;
    }

    @Override
    public int evaluate(final int x) {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || null != other && getClass() == other.getClass() && value == ((Const) other).value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
