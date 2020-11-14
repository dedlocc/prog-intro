package game.board;

import game.Cell;
import game.Move;
import game.Position;
import game.Result;

public interface Board {
    Position getPosition();

    Cell getTurn();

    Result move(Move move);

}
