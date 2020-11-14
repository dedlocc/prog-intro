package classworks.cw9;

import java.util.Arrays;

public final class Board implements Position {
    private final Cell[][] cells = new Cell[3][3];
    private Cell turn = Cell.X;

    public Board() {
        for (var r = 0; r < 3; ++r) {
            Arrays.fill(cells[r], Cell.E);
        }
    }

    @Override
    public Cell get(final int r, final int c) {
        return cells[r][c];
    }

    public Cell getTurn() {
        return turn;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && 3 > move.getRow() &&
            0 <= move.getColumn() && 3 > move.getColumn() &&
            Cell.E == get(move.getRow(), move.getColumn()) &&
            turn == move.getValue();
    }

    Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        var diag1 = 0;
        var diag2 = 0;
        var empty = 0;
        for (var i = 0; i < 3; ++i) {
            var rowCount = 0;
            var colCount = 0;

            for (var j = 0; j < 3; ++j) {
                if (Cell.E == get(i, j)) {
                    ++empty;
                } else if (turn == get(i, j)) {
                    ++rowCount;
                }
                if (turn == get(j, i)) {
                    ++colCount;
                }
            }
            if (3 == rowCount || 3 == colCount) {
                return Result.WIN;
            }
            if (turn == get(i, i)) {
                ++diag1;
            }
            if (turn == get(i, 2 - i)) {
                ++diag2;
            }
        }

        if (3 == diag1 || 3 == diag2) {
            return Result.WIN;
        }

        if (0 == empty) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;

        return Result.UNKNOWN;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder(" 123\n");
        for (int i = 0; i < 3; ++i) {
            sb.append(1 + i);
            for (final var cell : cells[i]) {
                if (Cell.E == cell) {
                    sb.append('.');
                } else {
                    sb.append(cell);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
