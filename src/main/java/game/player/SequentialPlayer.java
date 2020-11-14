package game.player;

import game.Cell;
import game.Move;
import game.Position;

public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        for (var row = 0; row < position.getRows(); ++row) {
            for (var column = 0; column < position.getColumns(); ++column) {
                if (Cell.EMPTY == position.get(row, column)) {
                    return new Move(row, column, cell);
                }
            }
        }
        throw new AssertionError("No empty cells");
    }
}
