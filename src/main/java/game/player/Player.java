package game.player;

import game.Cell;
import game.Move;
import game.Position;

public interface Player {
    Move move(final Position position, final Cell cell);
}
