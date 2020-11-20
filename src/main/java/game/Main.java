package game;

import game.board.Board;
import game.board.RhombusBoard;
import game.logger.SystemLogger;
import game.player.Player;
import game.player.RandomPlayer;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Main {
    public static void main(final String[] args) {
        final var in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter size and k (0 to exit): ");
            final int size, k;
            try {
                size = in.nextInt();
                if (0 == size) {
                    return;
                }
                k = in.nextInt();
            } catch (final InputMismatchException e) {
                System.out.println(e.getMessage());
                in.next();
                continue;
            }

            if (size <= 0 || k <= 0) {
                System.out.println("Only positive integers are allowed.");
                continue;
            }

            if (size < k) {
                System.out
                    .println("It's always a draw when k is greater than all dimensions. Please be more creative.");
                continue;
            }

            final Game game = new Game(new Player[]{new RandomPlayer(), new RandomPlayer()}, new SystemLogger());
            final Board board = new RhombusBoard(size, k);
            System.out.println("Game result: " + game.play(board));
        }
    }
}
