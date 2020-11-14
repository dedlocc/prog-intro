package game.player;

import game.Cell;
import game.Move;
import game.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        Move move;
        do {
            move = new Move(random.nextInt(position.getRows()), random.nextInt(position.getColumns()), cell);
        } while (!position.isValid(move));

        return move;
    }
}
