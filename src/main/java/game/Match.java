package game;

import game.board.Board;
import game.logger.DummyLogger;
import game.logger.Logger;
import game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Match extends Game {
    protected int winsRequired;
    protected final Logger logger;

    public Match(final List<Player> players, final int winsRequired, final Logger matchLogger, final Logger gameLogger) {
        super(players, gameLogger);
        this.winsRequired = winsRequired;
        logger = matchLogger;
    }

    public Match(final List<Player> players, final int winsRequired, final Logger logger) {
        this(players, winsRequired, logger, new DummyLogger());
    }

    public void setWinsRequired(final int winsRequired) {
        this.winsRequired = winsRequired;
    }

    @Override
    public int play(final Board board) {
        final var score = new int[players.size()];
        var maxWins = 0;
        var round = 1;

        final List<Player> players = new ArrayList<>(this.players);

        while (true) {
            final var result = super.play(board);
            logger.log(String.format("Round #%d final position:", round));
            logger.log(board.getPosition());
            if (0 == result) {
                logger.log(String.format("Round #%d has ended with a draw.", round));
            } else {
                final var index = Math.floorMod(result - round, players.size());
                if (maxWins < ++score[index]) {
                    maxWins = score[index];
                }

                logger.log(String.format("Player %d has won round #%d.", 1 + index, round));

                if (maxWins == winsRequired) {
                    logger.log("The final game score is " + formatScore(score));
                    logger.log(String.format("Player %d has won the match.", 1 + index));
                    return 1 + index;
                }
            }
            logger.log("Current game score is " + formatScore(score));
            ++round;
            board.reset();
            Collections.rotate(players, 1);
        }
    }

    private static String formatScore(final int[] score) {
        return Arrays.stream(score).mapToObj(Integer::toString).collect(Collectors.joining(":"));
    }
}
