package game.player;

import game.core.Cell;
import game.core.Move;
import game.core.Position;

@FunctionalInterface
public interface Player {
    Move move(final Position position, final Cell cell);
}
