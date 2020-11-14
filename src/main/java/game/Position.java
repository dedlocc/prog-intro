package game;

public interface Position {
    int getRows();

    int getColumns();

    Cell get(final int row, final int column);

    boolean isValid(final Move move);

    @Override
    String toString();
}
