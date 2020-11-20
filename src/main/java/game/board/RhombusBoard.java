package game.board;

import game.core.Cell;

public final class RhombusBoard extends MNKBoard {
    public RhombusBoard(final int size, final int k) {
        super(size, size, k, cells -> {
            // TODO get rid of doubles
            final var half1 = (size + 1) / 2.0;
            final var half2 = half1 - size % 2;
            final var delta = half1 - 1;

            for (var i = 0; i < size; ++i) {
                final var ai = Math.abs(i - delta);
                for (var j = 0; j < size; ++j) {
                    final var aj = Math.abs(j - delta);
                    cells[i][j] = ai + aj > half2 ? Cell.OUT : Cell.EMPTY;
                }
            }

            final var r = size + 1 - size % 2;
            return (r * r) / 2 + size % 2;
        });
    }
}
