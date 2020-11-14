package classworks.cw9;

import classworks.cw9.player.RandomPlayer;
import classworks.cw9.player.SequentialPlayer;

public final class Main {
    public static void main(final String[] args) {
        final var game = new Game(new RandomPlayer(), new SequentialPlayer(), true);
        System.out.println("Game result: " + game.play(new Board()));
    }
}
