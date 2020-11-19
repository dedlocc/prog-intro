package game.board;

import game.core.Cell;
import game.core.Move;
import game.core.Position;
import game.core.Result;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

public class MNKBoard implements Board {
    protected final int k;

    protected int emptyCells;

    private final Set<Cell> eliminated = EnumSet.noneOf(Cell.class);
    private final ClientPosition position;

    private int players = 2;
    private Cell turn = Cell.X;


    public MNKBoard(final int rows, final int columns, final int k) {
        this.k = k;
        position = new ClientPosition(rows, columns);
        reset();
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
    public void setPlayers(final int players) {
        this.players = players;
    }

    @Override
    public void reset() {
        position.clear();
        eliminated.clear();
        emptyCells = position.getRows() * position.getColumns();
    }

    @Override
    public Result move(final Move move) {
        if (!position.isValid(move)) {
            eliminated.add(turn);
            nextTurn();
            return Result.LOSE;
        }

        position.set(move.getRow(), move.getColumn(), move.getValue());
        --emptyCells;

        if (position.isWinning(move)) {
            return Result.WIN;
        }

        if (0 == emptyCells) {
            return Result.DRAW;
        }

        nextTurn();

        return Result.PASS;
    }

    private void nextTurn() {
        do {
            turn = turn.next(players);
        } while (eliminated.contains(turn));
    }

    private enum Direction {
        HORIZONTAL(0, 1),
        VERTICAL(1, 0),
        PRIMARY_DIAGONAL(1, 1),
        SECONDARY_DIAGONAL(1, -1);

        private final int rowStep;
        private final int columnStep;

        Direction(final int rowStep, final int columnStep) {
            this.rowStep = rowStep;
            this.columnStep = columnStep;
        }
    }

    private class ClientPosition implements Position {
        protected final int rows;
        protected final int columns;
        private final Cell[][] cells;

        private ClientPosition(final int rows, final int columns) {
            this.rows = rows;
            this.columns = columns;

            cells = new Cell[rows][columns];
        }

        private void clear() {
            for (final var row : position.cells) {
                Arrays.fill(row, Cell.EMPTY);
            }
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

        private void set(final int row, final int column, final Cell value) {
            cells[row][column] = value;
        }

        private boolean isInBounds(final int row, final int column) {
            return 0 <= row && row < rows && 0 <= column && column < columns;
        }

        @Override
        public boolean isValid(final int row, final int column) {
            return isInBounds(row, column) && Cell.EMPTY == get(row, column);
        }

        @Override
        public boolean isValid(final Move move) {
            return isValid(move.getRow(), move.getColumn()) && turn == move.getValue();
        }

        private int sequenceSize(final Move move, final int rowStep, final int columnStep) {
            final var targetCell = move.getValue();
            var size = 0;

            for (
                int r = move.getRow(), c = move.getColumn();
                isInBounds(r, c) && targetCell == cells[r][c] && size <= k;
                r += rowStep, c += columnStep
            ) {
                ++size;
            }

            return size;
        }

        @Override
        public boolean isWinning(final Move move) {
            for (final var dir : Direction.values()) {
                if (k < sequenceSize(move, dir.rowStep, dir.columnStep) + sequenceSize(move, -dir.rowStep, -dir.columnStep)) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public int getPlayers() {
            return players;
        }

        @Override
        public String toString() {
            final var sb = new StringBuilder("\t");
            for (int i = 1; i <= columns; ++i) {
                sb.append(i % 10);
            }
            for (int i = 0; i < rows; ++i) {
                sb.append('\n').append(1 + i).append('\t');
                for (final var cell : cells[i]) {
                    sb.append(cell);
                }
            }
            return sb.toString();
        }
    }
}
