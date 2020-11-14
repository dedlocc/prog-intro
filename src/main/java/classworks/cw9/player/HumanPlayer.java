package classworks.cw9.player;

import classworks.cw9.Cell;
import classworks.cw9.Move;
import classworks.cw9.Position;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(final Scanner in) {
        this.in = in;
    }

    public HumanPlayer() {
        this(new Scanner(System.in));
    }

    @Override
    public Move makeMove(final Position position, final Cell cell) {
        while (true) {
            System.out.println("Current position:");
            System.out.println(position);
            System.out.println("Enter " + cell + "'s move");
            System.out.print("Row: ");
            final var row = in.nextInt();
            System.out.print("Column: ");
            final var column = in.nextInt();
            final var move = new Move(row - 1, column - 1, cell);
            if (position.isValid(move)) {
                return move;
            }
            System.out.println("Invalid move: row " + row + ", column " + column);
        }
    }
}
