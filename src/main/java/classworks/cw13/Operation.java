package classworks.cw13;

public enum Operation {
    ADD {
        @Override
        int evaluate(final int x, final int y) {
            return x + y;
        }
    },
    SUBTRACT {
        @Override
        int evaluate(final int x, final int y) {
            return x - y;
        }
    },
    MULTIPLY {
        @Override
        int evaluate(final int x, final int y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        int evaluate(final int x, final int y) {
            return x - y;
        }
    };

    abstract int evaluate(final int x, final int y);
}
