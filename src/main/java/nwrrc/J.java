package nwrrc;

import java.util.Scanner;

public final class J {
    public static void main(final String[] args) {
        final var in = new Scanner(System.in);
        final var n = in.nextShort();

        final var a = new byte[n][n];

        for (short i = 0; i < n; ++i) {
            final var s = in.next();
            for (short j = 0; j < n; ++j) {
                a[i][j] = (byte) (s.charAt(j) - '0');
            }
        }

        for (short i = 0; i < n; ++i) {
            for (short j = 0; j < n; ++j) {
                System.out.print(a[i][j]);
                if (0 != a[i][j]) {
                    for (short k = (short) (j + 1); k < n; ++k) {
                        a[i][k] = (byte) ((10 + a[i][k] - a[j][k]) % 10);
                    }
                }
            }
            System.out.println();
        }
    }
}
