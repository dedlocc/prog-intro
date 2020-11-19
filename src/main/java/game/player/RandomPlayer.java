package game.player;

import game.core.Cell;
import game.core.Move;
import game.core.Position;

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
        int row, column;
        do {
            row = random.nextInt(position.getRows());
            column = random.nextInt(position.getColumns());
        } while (!position.isValid(row, column));

        return new Move(row, column, cell);
    }
}
