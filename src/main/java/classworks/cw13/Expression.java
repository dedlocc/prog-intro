package classworks.cw13;

@FunctionalInterface
public interface Expression {
    /* public static final */ String HELLO = "world";

    int evaluate(final int x);

    default int getPriority() {
        return 0;
    }

    static Expression constant(final int value) {
        return x -> value;
    }

    static Expression x() {
        return x -> x;
    }

    static Expression binary(final Operation operation, final Expression a, final Expression b) {
        //return x -> operation.evaluate(a.evaluate(x), b.evaluate(x));
        return new Expression() {
            @Override
            public int evaluate(final int x) {
                return operation.evaluate(a.evaluate(x), b.evaluate(x));
            }

            @Override
            public int getPriority() {
                return 1;
            }
        };
    }
}
