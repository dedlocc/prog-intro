package game;

import game.board.Board;
import game.logger.DummyLogger;
import game.logger.Logger;
import game.player.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Match extends Game {
    protected int winsRequired;
    protected final Player[] originalPlayers;
    protected final Logger logger;

    public Match(final Player[] players, final int winsRequired, final Logger matchLogger, final Logger gameLogger) {
        super(players, gameLogger);
        this.winsRequired = winsRequired;
        originalPlayers = players;
        logger = matchLogger;
    }

    public Match(final Player[] players, final int winsRequired, final Logger logger) {
        this(players, winsRequired, logger, new DummyLogger());
    }

    public void setWinsRequired(final int winsRequired) {
        this.winsRequired = winsRequired;
    }

    @Override
    public int play(final Board board) {
        final var score = new int[players.length];
        var maxWins = 0;
        var round = 1;

        try {
            while (true) {
                final var result = super.play(board);
                logger.log(String.format("Round #%d final position:", round));
                logger.log(board.getPosition());
                if (0 == result) {
                    logger.log(String.format("Round #%d has ended with a draw.", round));
                } else {
                    final var index = Math.floorMod(result - round, players.length);
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
                shiftPlayers();
            }
        } finally {
            System.arraycopy(originalPlayers, 0, players, 0, players.length);
        }
    }

    private static String formatScore(final int[] score) {
        return Arrays.stream(score).mapToObj(Integer::toString).collect(Collectors.joining(":"));
    }

    private void shiftPlayers() {
        final var t = players[0];
        System.arraycopy(players, 1, players, 0, players.length - 1);
        players[players.length - 1] = t;
    }
}
