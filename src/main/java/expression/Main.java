package expression;

import java.util.Scanner;

public final class Main {
    public static void main(final String[] args) {
        final var x = new Variable("x");
        final var expr = new Add(new Subtract(new Multiply(x, x), new Multiply(new Const(2), x)), new Const(1));

        final var in = new Scanner(System.in);
        System.out.print("Enter X: ");
        while (true) {
            if (in.hasNextInt()) {
                System.out.printf("Answer: %d%n", expr.evaluate(in.nextInt()));
                break;
            } else {
                System.out.println();
                System.out.println("X must be an integer. Please try again.");
                in.next();
            }
        }
    }
}
