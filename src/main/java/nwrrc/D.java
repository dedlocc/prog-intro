package nwrrc;

import java.util.Scanner;

public final class D {
    private static final int MOD = 998_244_353;
    private static long[] pow;
    private static long[] dCache;

    public static void main(final String[] args) {
        final var in = new Scanner(System.in);
        final var n = in.nextInt();
        final var k = in.nextInt();

        pow = new long[n + 1];
        dCache = new long[n + 1];

        pow[0] = 1;
        for (var i = 1; i <= n; ++i) {
            pow[i] = pow[i - 1] * k % MOD;
        }

        var r = 0;
        for (var i = 1; i <= n; ++i) {
            r += dCache[i] = d(i);
            r %= MOD;
            if (1 < i) {
                final var to = (int) Math.sqrt(i);
                for (var j = 1; j <= to; ++j) {
                    if (0 == i % j) {
                        r += dCache[j];
                        r %= MOD;
                        if (1 != j && i / j != j) {
                            r += dCache[i / j];
                            r %= MOD;
                        }
                    }
                }
            }
        }

        System.out.println(Math.floorMod(r, MOD));
    }

    private static int r(final int n) {
        if (0 == n % 2) {
            return (int) (n / 2 * pow[n / 2] % MOD + n / 2 * pow[n / 2 + 1] % MOD);
        } else {
            return (int) (n * pow[n / 2 + 1] % MOD);
        }
    }

    private static int d(final int n) {
        var r = r(n);

        if (1 < n) {
            final var to = (int) Math.sqrt(n);
            for (var i = 1; i <= to; ++i) {
                if (0 == n % i) {
                    r -= n / i * dCache[i] % MOD;
                    r %= MOD;
                    if (1 != i && n / i != i) {
                        r -= i * dCache[n / i] % MOD;
                        r %= MOD;
                    }
                }
            }
        }

        return r;
    }
}
