package game;

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
            result = makeMove(board, 1 == currentPlayer ? this.player1 : this.player2, currentPlayer);
            if (-1 != result) {
                break;
            }

            currentPlayer = 3 - currentPlayer;
        }

        System.out.print("Game result: ");
        if (0 == result) {
            System.out.println("draw");
        } else {
            System.out.println("player " + result + " won");
        }

        return result;
        
    }

    private int makeMove(final Board board, final Player player, final int no) {
        final var move = player.makeMove(board, board.getTurn());
        if (enableLogging) {
            System.out.println("Move: " + move);
        }

        final var result = board.makeMove(move);

        if (enableLogging) {
            System.out.println(board);
        }

        return switch (result) {
            case WIN -> no;
            case LOSE -> 3 - no;
            case DRAW -> 0;
            case UNKNOWN -> -1;
            default -> throw new AssertionError("Unsupported result type: " + result);
        };
    }
}
