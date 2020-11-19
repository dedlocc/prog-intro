package game;

import game.board.Board;
import game.logger.Logger;
import game.player.Player;

import java.util.Arrays;

public class Game {
    protected final Player[] players;
    protected final Logger logger;
    private final boolean[] eliminated;

    public Game(final Player[] players, final Logger logger) {
        this.players = players;
        this.logger = logger;
        this.eliminated = new boolean[players.length];
    }

    int play(final Board board) {
        board.setPlayers(players.length);
        int result;
        int currentPlayer = 0;

        try {
            while (true) {
                result = move(board, players[currentPlayer], 1 + currentPlayer);
                if (-1 != result) {
                    break;
                }

                logger.log(board.getPosition());

                do {
                    currentPlayer = (1 + currentPlayer) % players.length;
                } while (eliminated[currentPlayer]);
            }
        } finally {
            Arrays.fill(eliminated, false);
        }

        logger.log("Final position:");
        logger.log(board.getPosition());

        if (0 == result) {
            logger.log("Game result: draw");
        } else {
            logger.log("Game result: player " + result + " won");
        }

        return result;
    }

    private int move(final Board board, final Player player, final int id) {
        final var move = player.move(board.getPosition(), board.getTurn());
        logger.log("Move: " + move);

        final var result = board.move(move);

        return switch (result) {
            case WIN -> id;
            case LOSE -> {
                eliminated[id] = true;
                int no2 = -1;
                for (var i = 0; i < eliminated.length; ++i) {
                    if (!eliminated[i]) {
                        if (-1 != no2) {
                            yield -1;
                        }
                        no2 = i;
                    }
                }
                yield -1 == no2 ? 0 : no2;
            }
            case DRAW -> 0;
            case PASS -> -1;
            default -> throw new AssertionError("Unsupported result type: " + result);
        };
    }
}
