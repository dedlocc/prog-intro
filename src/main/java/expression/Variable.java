package expression;

public class Variable implements Expression {
    private final String name;

    public Variable(final String name) {
        this.name = name;
    }

    @Override
    public int evaluate(final int x) {
        return x;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || null != other && getClass() == other.getClass() && name.equals(((Variable) other).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
