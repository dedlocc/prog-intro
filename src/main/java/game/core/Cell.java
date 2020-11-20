package game.core;

public enum Cell {
    X('X'),
    O('O'),
    D('-'),
    B('|'),
    EMPTY('.'),
    OUT(' ');

    public static final int MAX_PLAYERS = 4;

    final char symbol;

    Cell(final char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    public Cell next(final int numOfPlayers) {
        assert MAX_PLAYERS >= numOfPlayers;

        return values()[(1 + ordinal()) % numOfPlayers];
    }
}
