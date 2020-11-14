package game.player;

import game.Cell;
import game.Move;
import game.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move makeMove(final Position position, final Cell cell) {
        Move move;
        do {
            move = new Move(random.nextInt(3), random.nextInt(3), cell);
        } while (!position.isValid(move));

        return move;
    }
}
