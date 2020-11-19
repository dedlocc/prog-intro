package game.player;

import game.core.Cell;
import game.core.Move;
import game.core.Position;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Current position:");
            out.println(position);
            out.print("Enter " + cell + "'s move (row and column): ");

            final int row, column;
            try {
                row = in.nextInt();
                column = in.nextInt();
            } catch (final InputMismatchException e) {
                out.println("Invalid move: only integers are allowed");
                continue;
            }

            final var move = new Move(row - 1, column - 1, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Invalid move: row " + row + ", column " + column);
        }
    }
}
