package expression;

import expression.parser.ExpressionParser;

import java.util.Scanner;

public final class Main {
    public static void main(final String[] args) {
        final var in = new Scanner(System.in);
        final var expression = in.nextLine();
        final var parser = new ExpressionParser();

        System.out.println(parser.parse(expression));
        System.out.println(parser.parse(expression).toMiniString());

        while (true) {
            System.out.print("Enter X: ");
            if (in.hasNextInt()) {
                System.out.println(parser.parse(expression).evaluate(in.nextInt()));
                break;
            } else {
                System.out.println();
                System.out.println("X must be an integer. Please try again.");
                in.next();
            }
        }
    }
}
