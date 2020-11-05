package nwrrc;

import java.util.HashMap;
import java.util.Scanner;

public final class M {
    public static void main(final String[] args) {
        final var in = new Scanner(System.in);

        final var t = in.nextByte();

        for (var ti = 0; ti < t; ++ti) {
            final var n = in.nextInt();
            final var a = new int[n];

            for (var i = 0; i < n; ++i) {
                a[i] = in.nextInt();
            }

            int result = 0;
            final var c = new HashMap<Integer, Integer>();

            for (var j = n - 1; j > 0; --j) {
                for (var i = 0; i < j; ++i) {
                    final var ak = 2 * a[j] - a[i];
                    if (c.containsKey(ak)) {
                        result += c.get(ak);
                    }
                }
                // Increment a[j] if present or set to 1 otherwise
                c.merge(a[j], 1, Integer::sum);
            }

            System.out.println(result);
        }
    }
}
