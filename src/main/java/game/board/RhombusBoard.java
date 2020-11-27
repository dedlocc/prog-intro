package game.board;

import game.core.Cell;

public final class RhombusBoard extends MNKBoard {
    public RhombusBoard(final int size, final int k) {
        super(size, size, k, cells -> {
            final var half = size / 2;
            var span = (size - 1) / 2;

            var emptyCells = 0;

            for (var i = 0; i < size; ++i) {
                for (var j = 0; j < size; ++j) {
                    if (j >= span && j < size - span) {
                        cells[i][j] = Cell.EMPTY;
                        ++emptyCells;

                    } else {
                        cells[i][j] = Cell.OUT;
                    }
                }

                if (i >= half) {
                    ++span;
                } else if (0 != span) {
                    --span;
                }
            }

            return emptyCells;
        });
    }
}
