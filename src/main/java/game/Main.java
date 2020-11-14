package game;

import game.board.MNKBoard;
import game.player.HumanPlayer;

public final class Main {
    public static void main(final String[] args) {
        final var game = new Game(new HumanPlayer(), new HumanPlayer(), true);
        System.out.println("Game result: " + game.play(new MNKBoard(3, 3, 3)));
    }
}
