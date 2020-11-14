package game;

import game.board.Board;
import game.player.Player;

public class Game {
    private final Player player1;
    private final Player player2;
    private final boolean enableLogging;

    public Game(final Player player1, final Player player2, final boolean enableLogging) {
        this.player1 = player1;
        this.player2 = player2;
        this.enableLogging = enableLogging;
    }

    int play(final Board board) {
        int result;
        int currentPlayer = 1;
        while (true) {
            result = move(board, 1 == currentPlayer ? player1 : player2, currentPlayer);
            if (-1 != result) {
                break;
            }

            if (enableLogging) {
                System.out.println(board.getPosition());
            }

            currentPlayer = 3 - currentPlayer;
        }

        System.out.println("Final position:");
        System.out.println(board.getPosition());

        System.out.print("Game result: ");
        if (0 == result) {
            System.out.println("draw");
        } else {
            System.out.println("player " + result + " won");
        }

        return result;
    }

    private int move(final Board board, final Player player, final int no) {
        final var move = player.move(board.getPosition(), board.getTurn());
        if (enableLogging) {
            System.out.println("Move: " + move);
        }

        final var result = board.move(move);

        return switch (result) {
            case WIN -> no;
            case LOSE -> 3 - no;
            case DRAW -> 0;
            case PASS -> -1;
            default -> throw new AssertionError("Unsupported result type: " + result);
        };
    }
}
