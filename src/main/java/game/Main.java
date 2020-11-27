package game;

import game.board.RhombusBoard;
import game.logger.SystemLogger;
import game.player.RandomPlayer;

import java.util.List;

public final class Main {
    public static void main(final String[] args) {
        final Game game = new Game(List.of(
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer()
        ), new SystemLogger());

        final var reader = new PositiveIntegerReader();

        while (true) {
            System.out.print("Enter size and k (0 to exit): ");
            final int size, k;

            size = reader.read(true);

            if (-1 == size) {
                System.out.println("Size can only be a positive integer.");
                continue;
            } else if (0 == size) {
                return;
            }

            k = reader.read();

            if (-1 == k) {
                System.out.println("K can only be a positive integer.");
                continue;
            }

            if (size < k) {
                System.out
                    .println("It's always a draw when k is greater than all dimensions. Please be more creative.");
            } else {
                System.out.println("Game result: " + game.play(new RhombusBoard(size, k)));
            }
        }
    }
}
