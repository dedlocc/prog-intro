package nwrrc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public final class H {
    public static void main(final String[] args) {
        final var in = new Scanner(System.in);
        final var n = in.nextInt();

        final var a = new int[n];
        var maxA = 1;
        var f = new int[n];
        var sums = new int[n];
        var fc = 0;

        for (int i = 0; i < n; ++i) {
            a[i] = in.nextInt();
            if (maxA < a[i]) {
                maxA = a[i];
            }
            final var end = fc + a[i];
            if (f.length < end) {
                f = Arrays.copyOf(f, Math.max(2 * f.length, end));
            }
            Arrays.fill(f, fc, end, i);
            fc = end;

            if (0 != i) {
                sums[i] = sums[i - 1] + a[i - 1];
            }
        }

        final var q = in.nextInt();
        final var cache = new HashMap<Integer, Integer>();

        for (int i = 0; i < q; ++i) {
            final var t = in.nextInt();
            if (t < maxA) {
                System.out.println("Impossible");
                continue;
            }

            if (cache.containsKey(t)) {
                System.out.println(cache.get(t));
                continue;
            }

            var j = 1;
            var b = 0;
            while (b + t < fc) {
                b = sums[f[b + t]];
                ++j;
            }

            cache.put(t, j);
            System.out.println(j);
        }
    }
}
