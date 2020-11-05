package nwrrc;

import java.util.Scanner;

public final class A {
    public static void main(final String[] args) {
        final var scanner = new Scanner(System.in);
        final var a = scanner.nextInt();
        final var b = scanner.nextInt();
        final var n = scanner.nextInt();

        System.out.println(1 + 2 * (int) Math.ceil((double) (n - b) / (b - a)));
    }
}