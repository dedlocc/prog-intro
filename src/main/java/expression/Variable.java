package expression;

import expression.exceptions.EvaluationException;

public final class Variable implements CommonExpression {
    private final String name;

    public Variable(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int evaluate(final int x) {
        if ("x".equals(name)) {
            return x;
        }

        throw new NoValueException();
    }

    @Override
    public double evaluate(final double x) {
        if ("x".equals(name)) {
            return x;
        }

        throw new NoValueException();
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new NoValueException();
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

    public class NoValueException extends EvaluationException {
        private NoValueException() {
            super(String.format("No value provided for variable \"%s\"", name));
        }
    }
}
