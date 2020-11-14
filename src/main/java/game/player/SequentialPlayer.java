package game.player;

import game.Cell;
import game.Move;
import game.Position;

public class SequentialPlayer implements Player {
    @Override
    public Move makeMove(final Position position, final Cell cell) {
        for (var row = 0; row < 3; ++row) {
            for (var column = 0; column < 3; ++column) {
                if (Cell.E == position.get(row, column)) {
                    return new Move(row, column, cell);
                }
            }
        }
        throw new AssertionError("No empty cells");
    }
}
