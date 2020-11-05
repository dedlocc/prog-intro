package nwrrc;

import java.util.Scanner;

public final class B {
    private static final int STEP = 710;
    private static final int RANGE = 50_000 / 2;

    public static void main(final String[] args) {
        final var n = new Scanner(System.in).nextInt();

        final var start = -RANGE * STEP;
        final var end = start + STEP * n;

        for (int i = start; i < end; i += STEP) {
            System.out.println(i);
        }
    }
}
