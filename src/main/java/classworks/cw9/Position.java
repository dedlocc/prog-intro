package classworks.cw9;

public interface Position {
    Cell get(final int r, final int c);

    boolean isValid(final Move move);
}
