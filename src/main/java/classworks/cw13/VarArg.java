package classworks.cw13;

import java.util.Arrays;

public final class VarArg {
    public static int min(final int b) {
        return b;
    }

    public static int min(final int a, final int... numbers) {
        int min = a;
        for (final var n : numbers) {
            if (min > n) {
                min = n;
            }
        }

        System.out.format("min(%d, %s) = %d (%d)%n", a, Arrays.toString(numbers), min, numbers.length);
        return min;
    }

    public static void main(final String[] args) {
        System.out.println(min(1));
        System.out.println(min(1, -1));
        System.out.println(min(1, -1, 10, -30, 40));
        System.out.println(min(-3, new int[]{10, 20, 30}));
    }
}
