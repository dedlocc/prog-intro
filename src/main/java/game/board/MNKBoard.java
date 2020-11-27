package game.board;

import game.core.Cell;
import game.core.Move;
import game.core.Position;
import game.core.Result;

import java.util.EnumSet;
import java.util.Set;

abstract class MNKBoard implements Board {
    protected Cell turn = Cell.X;
    protected MNKPosition position;

    private int players = 2;
    private final Set<Cell> eliminated = EnumSet.noneOf(Cell.class);

    protected MNKBoard() {
    }

    protected MNKBoard(final int rows, final int columns, final int k, final FillStrategy fillStrategy) {
        position = new MNKPosition(rows, columns, k, fillStrategy);
    }

    @Override
    public Position getPosition() {
        return position; // TODO improve cheat protection
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
    }

    @Override
    public Result move(final Move move) {
        if (!position.isValid(move)) {
            eliminated.add(turn);
            nextTurn();
            return Result.LOSE;
        }

        position.set(move.getRow(), move.getColumn(), move.getValue());

        if (position.isWinning(move)) {
            return Result.WIN;
        }

        if (position.isDraw()) {
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

    protected class MNKPosition implements Position {
        protected final Cell[][] cells;
        protected final int rows;
        protected final int columns;
        protected final int k;
        protected final FillStrategy fillStrategy;
        protected int emptyCells;

        protected MNKPosition(final int rows, final int columns, final int k, final FillStrategy fillStrategy) {
            this.rows = rows;
            this.columns = columns;
            this.k = k;
            this.fillStrategy = fillStrategy;

            cells = new Cell[rows][columns];
            clear();
        }

        protected void clear() {
            emptyCells = fillStrategy.fill(cells);
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

        protected void set(final int row, final int column, final Cell value) {
            if (Cell.EMPTY == cells[row][column] && Cell.EMPTY != value) {
                --emptyCells;
            }
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

        protected int sequenceSize(final Move move, final int rowStep, final int columnStep) {
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
        public boolean isDraw() {
            return 0 == emptyCells;
        }

        private int numOfDigits(int n) {
            var i = 0;
            while (n > 0) {
                ++i;
                n /= 10;
            }
            return i;
        }

        private void addSpaces(final StringBuilder sb, final int n) {
            sb.append(" ".repeat(n));
        }

        @Override
        public String toString() {
            final var rd = numOfDigits(rows);
            final var cd = numOfDigits(columns);

            final var sb = new StringBuilder();


            var digits = 1;
            var bound = 10;

            addSpaces(sb, rd);
            for (var i = 1; i <= columns; ++i) {
                if (i == bound) {
                    ++digits;
                    bound *= 10;
                }
                sb.append(' ').append(i);
                addSpaces(sb, cd - digits);
            }

            digits = 0;
            bound = 10;

            for (var i = 0; i < rows; ++i) {
                sb.append('\n').append(1 + i);
                if (1 + i == bound) {
                    ++digits;
                    bound *= 10;
                }
                addSpaces(sb, rd - digits);

                for (int j = 0; j < columns; ++j) {
                    sb.append(cells[i][j]);
                    addSpaces(sb, cd);
                }
            }

            return sb.toString();
        }
    }
}
