package game.board;

import game.core.Cell;

@FunctionalInterface
public interface FillStrategy {
    int fill(final Cell[][] cells);
}
