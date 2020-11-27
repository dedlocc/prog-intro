package expression;

public class Variable implements CommonExpression {
    private final String name;

    public Variable(final String name) {
        this.name = name;
    }

    @Override
    public int evaluate(final int x) {
        return x;
    }

    @Override
    public double evaluate(final double x) {
        return x;
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalArgumentException(String.format("No value provided for variable \"%s\"", name));
        };
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
