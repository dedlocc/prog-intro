package game.board;

import game.Cell;
import game.Move;
import game.Position;
import game.Result;

import java.util.Arrays;

public class MNKBoard implements Board {
    private static final int[][] DIRECTIONS = {
        {0, 1}, // Horizontal
        {1, 0}, // Vertical
        {1, 1}, // First diagonal
        {1, -1} // Second diagonal
    };
    private final MNKPosition position;
    private Cell turn = Cell.X;

    public MNKBoard(final int m, final int n, final int k) {
        position = new MNKPosition(n, m, k);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Result move(final Move move) {
        if (!position.applyMove(move)) {
            return Result.LOSE;
        }

        if (position.isWin(move)) {
            return Result.WIN;
        }

        if (position.isDraw()) {
            return Result.DRAW;
        }

        turn = Cell.X == turn ? Cell.O : Cell.X;
        return Result.PASS;
    }

    private class MNKPosition implements Position {
        private final int rows;
        private final int columns;
        private final int k;
        private final Cell[][] cells;
        private int emptyCells;

        public MNKPosition(final int rows, final int columns, final int k) {
            this.rows = rows;
            this.columns = columns;
            this.k = k;

            cells = new Cell[rows][columns];
            for (final var row : cells) {
                Arrays.fill(row, Cell.EMPTY);
            }

            emptyCells = rows * columns;
        }

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public Cell get(final int row, final int column) {
            return cells[row][column];
        }

        @Override
        public boolean isValid(final Move move) {
            final var row = move.getRow();
            final var column = move.getColumn();

            return 0 <= row && row < rows
                && 0 <= column && column < columns
                && Cell.EMPTY == get(row, column)
                && turn == move.getValue();
        }

        @Override
        public String toString() {
            final var sb = new StringBuilder("\t");
            for (int i = 1; i <= columns; ++i) {
                sb.append(i % 10);
            }
            sb.append('\n');
            for (int i = 0; i < rows; ++i) {
                sb.append(1 + i).append('\t');
                for (final var cell : cells[i]) {
                    sb.append(cell);
                }
                sb.append('\n');
            }
            return sb.toString();
        }

        private boolean applyMove(final Move move) {
            if (!isValid(move)) {
                return false;
            }

            cells[move.getRow()][move.getColumn()] = move.getValue();
            --emptyCells;

            return true;
        }

        private int sequenceSize(final Move move, final int rowStep, final int columnStep) {
            final var targetCell = move.getValue();
            var size = 0;

            for (
                int r = move.getRow(), c = move.getColumn();
                r >= 0 && c >= 0 && r < rows && c < columns && targetCell == cells[r][c];
                ++size
            ) {
                r += rowStep;
                c += columnStep;
            }

            return size;
        }

        private boolean isWin(final Move move) {
            for (final var dir : DIRECTIONS) {
                if (k + 1 <= sequenceSize(move, dir[0], dir[1]) + sequenceSize(move, -dir[0], -dir[1])) {
                    return true;
                }
            }

            return false;
        }

        private boolean isDraw() {
            return 0 == emptyCells;
        }
    }
}
