package expression;

@FunctionalInterface
public interface PrecedenceAware {
    Precedence getPrecedence();

    default <T> boolean hasLowerPrecedence(final T other, final boolean right) {
        if (!(other instanceof PrecedenceAware)) {
            return false;
        }

        final var p = (PrecedenceAware) other;

        final var comp = getPrecedence().compareTo(p.getPrecedence());
        return 0 < comp || right && 0 == comp && lowerIfSame(p);
    }

    default boolean lowerIfSame(final PrecedenceAware expr) {
        return false;
    }
}
