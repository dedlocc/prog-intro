package game;

import game.board.Board;
import game.logger.DummyLogger;
import game.logger.Logger;
import game.player.Player;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Tournament {
    protected int rounds;
    protected final List<Player> players;
    protected final Logger logger;
    protected final Logger gameLogger;

    public Tournament(final List<Player> players, final int rounds, final Logger tournamentLogger, final Logger gameLogger) {
        this.players = players;
        this.rounds = rounds;
        this.logger = tournamentLogger;
        this.gameLogger = gameLogger;
    }

    public Tournament(final List<Player> players, final int rounds, final Logger logger) {
        this(players, rounds, logger, new DummyLogger());
    }

    public Map<Integer, Integer> play(final Board board) {
        final var points = new int[players.size()];
        var gameNumber = 0;

        for (int round = 1; round <= rounds; ++round) {
            logger.log(String.format("Round #%d.", round));

            for (int p1 = 0; p1 < players.size(); ++p1) {
                for (int p2 = p1 + 1; p2 < players.size(); ++p2) {
                    final var result = new Game(List.of(players.get(p1), players.get(p2)), gameLogger).play(board);
                    logger.log(String.format("Game %d: %d vs %d. Final position:", ++gameNumber, 1 + p1, 1 + p2));
                    logger.log(board.getPosition());

                    if (0 == result) {
                        ++points[p1];
                        ++points[p2];
                        logger.log(String.format("Game %d has ended with a draw.", gameNumber));
                    } else if (3 > result) {
                        final var p = 1 == result ? p1 : p2;
                        points[p] += 3;
                        logger.log(String.format("Player %d has won game %d.", 1 + p, gameNumber));
                    } else {
                        throw new AssertionError("Invalid game result");
                    }

                    board.reset();
                }
            }
        }

        final var indices = new Integer[players.size()];
        Arrays.setAll(indices, i -> i);
        Arrays.sort(indices, (i, j) -> Integer.compare(points[j], points[i]));

        final Map<Integer, Integer> results = new LinkedHashMap<>();

        for (final var i : indices) {
            results.put(i, points[i]);
        }

        return results;
    }
}