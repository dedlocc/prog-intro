package game;

import game.board.Board;
import game.board.MNKBoard;
import game.logger.SystemLogger;
import game.player.Player;
import game.player.RandomPlayer;

public final class Main {
    public static void main(final String[] args) {
        final var tournament = new Tournament(new Player[]{
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
        }, 5, new SystemLogger());

        final Board board = new MNKBoard(4, 4, 3);

        final var results = tournament.play(board);

        System.out.println("Tournament has ended! Result table:");
        var i = 0;
        for (final var e : results.entrySet()) {
            System.out.printf("%d. Player %d - %d\n", ++i, 1 + e.getKey(), e.getValue());
        }
    }
}
