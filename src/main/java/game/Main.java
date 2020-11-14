package game;

import game.player.RandomPlayer;
import game.player.SequentialPlayer;

public final class Main {
    public static void main(final String[] args) {
        final var game = new Game(new RandomPlayer(), new SequentialPlayer(), true);
        System.out.println("Game result: " + game.play(new Board()));
    }
}
