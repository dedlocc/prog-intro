package expression.exceptions;

import expression.TripleExpression;

public final class Main {
    public static void main(final String[] args) {
        final var parser = new ExpressionParser();
        final TripleExpression expression;

        try {
            expression = parser.parse("1000000*x*x*x*x*x/(x-1)");
        } catch (final ParseException e) {
            System.out.println("Unable to parse expression: " + e.getMessage());
            return;
        }

        System.out.println("x\tf");

        for (int i = 0; i <= 10; ++i) {
            System.out.printf("%d\t", i);
            try {
                System.out.println(expression.evaluate(i, 0, 0));
            } catch (final OverflowException e) {
                System.out.println("overflow");
            } catch (final DivisionByZeroException e) {
                System.out.println("division by zero");
            } catch (final EvaluationException e) {
                System.out.println("unexpected evaluation error");
            }
        }
    }
}
