package classworks.cw9.player;

import classworks.cw9.Cell;
import classworks.cw9.Move;
import classworks.cw9.Position;

public interface Player {
    Move makeMove(final Position position, final Cell cell);
}
