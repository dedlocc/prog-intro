package game.player;

import game.PositiveIntegerReader;
import game.core.Cell;
import game.core.Move;
import game.core.Position;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final PositiveIntegerReader in;

    public HumanPlayer(final PrintStream out, final PositiveIntegerReader in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new PositiveIntegerReader(new Scanner(System.in)));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Current position:");
            out.println(position);
            out.print("Enter " + cell + "'s move (row and column): ");

            final int row, column;
            row = in.read();
            column = in.read();

            if (-1 == row || -1 == column) {
                out.println("Invalid move: only positive integers are allowed");
            } else {
                final var move = new Move(row - 1, column - 1, cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println("Invalid move: row " + row + ", column " + column);
            }
        }
    }
}
