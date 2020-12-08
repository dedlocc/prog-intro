package classworks.cw13;

public final class Main {
    public static void main(final String[] args) {
        final var expression = Expression.binary(
            Operation.ADD,
            Expression.constant(10),
            Expression.x()
        );
    }
}
