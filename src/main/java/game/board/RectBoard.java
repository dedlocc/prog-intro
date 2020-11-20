package game.board;

import game.core.Cell;

import java.util.Arrays;

public final class RectBoard extends MNKBoard {
    public RectBoard(final int rows, final int columns, final int k) {
        super(rows, columns, k, cells -> {
            for (var i = 0; i < rows; ++i) {
                Arrays.fill(cells[i], Cell.EMPTY);
            }
            return rows * columns;
        });
    }
}
