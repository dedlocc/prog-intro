package game.board;

import game.core.Cell;
import game.core.Move;
import game.core.Position;
import game.core.Result;

public interface Board {
    Result move(final Move move);

    Position getPosition();

    Cell getTurn();

    void setPlayers(final int players);

    void reset();
}
