package game.core;

public interface Position {
    int getRows();

    int getColumns();

    Cell get(final int row, final int column);

    boolean isValid(final int row, final int column);

    boolean isValid(final Move move);

    boolean isWinning(final Move move);

    int getPlayers();
}
