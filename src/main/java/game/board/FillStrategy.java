package game.board;

import game.core.Cell;

public interface FillStrategy {
    /**
     * Fill 2d board with cells
     *
     * @param cells Two-dimensional array of Cells to fill
     * @return Initial number of empty cells
     */
    int fill(final Cell[][] cells);
}
