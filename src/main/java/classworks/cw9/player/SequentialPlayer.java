package classworks.cw9.player;

import classworks.cw9.Cell;
import classworks.cw9.Move;
import classworks.cw9.Position;

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
