package expression.exceptions;

import expression.TripleExpression;

public final class Main {
    public static void main(final String[] args) {
        final var parser = new ExpressionParser();
        final TripleExpression expression;

        try {
            expression = parser.parse("sqrtx");
        } catch (final ParseException e) {
            System.out.println("Unable to parse expression: " + e.getMessage());
            return;
        }

        System.out.println("x\tf");

        for (int i = 0; i <= 20; ++i) {
            System.out.printf("%d\t", i);
            try {
                System.out.println(expression.evaluate(i, 0, 0));
            } catch (final EvaluationException e) {
                System.out.println(e.getReason());
            }
        }
    }
}
