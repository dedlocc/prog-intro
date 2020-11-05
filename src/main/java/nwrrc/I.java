package nwrrc;

import java.util.Scanner;

public final class I {
    public static void main(final String[] args) {
        final var in = new Scanner(System.in);
        final var n = in.nextInt();

        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;

        for (var i = 0; i < n; ++i) {
            final var x = in.nextInt();
            final var y = in.nextInt();
            final var h = in.nextInt();

            x1 = Math.min(x1, x - h);
            y1 = Math.min(y1, y - h);
            x2 = Math.max(x2, x + h);
            y2 = Math.max(y2, y + h);
        }

        System.out.printf("%d %d %d%n",
            (x1 + x2) / 2,
            (y1 + y2) / 2,
            (int) Math.ceil(Math.max(x2 - x1, y2 - y1) / 2.0)
        );
    }
}
