package game.player;

import game.core.Cell;
import game.core.Move;
import game.core.Position;

public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        for (var row = 0; row < position.getRows(); ++row) {
            for (var column = 0; column < position.getColumns(); ++column) {
                if (position.isValid(row, column)) {
                    return new Move(row, column, cell);
                }
            }
        }
        throw new AssertionError("No available cells");
    }
}
