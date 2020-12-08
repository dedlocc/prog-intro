package expression.parser;

import expression.*;
import expression.binary.*;
import expression.unary.Count;
import expression.unary.Negate;
import expression.unary.Not;

import java.text.CharacterIterator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public final class ExpressionParser implements Parser {
    private CharSource chars;

    @Override
    public CommonExpression parse(final String expression) throws UnexpectedTokenException {
        chars = new CharSequenceSource(expression);
        return parseExpression(CharacterIterator.DONE);
    }

    private CommonExpression parseExpression(final char until) {
        final Deque<CommonExpression> elements = new ArrayDeque<>();
        final Deque<BiOperation> operations = new ArrayDeque<>();

        while (!chars.test(until)) {
            if (!elements.isEmpty()) {
                final var operation = parseOperation();

                applyOperations(elements, operations, () -> operation.hasLowerPrecedence(operations.peek(), true));

                operations.push(operation);
            }

            elements.push(parseElement());
            skipWhitespace();
        }

        applyOperations(elements, operations, Condition::never);

        return elements.pop();
    }

    private static void applyOperations(
        final Deque<CommonExpression> elements,
        final Deque<BiOperation> operations,
        final Condition breakCondition
    ) {
        if (operations.isEmpty() || breakCondition.test()) {
            return;
        }

        var element = elements.pop();

        while (!elements.isEmpty() && !breakCondition.test()) {
            element = operations.pop().apply(elements.pop(), element);
        }

        elements.push(element);
    }

    private CommonExpression parseElement() {
        skipWhitespace();

        if (chars.test('(')) {
            return parseExpression(')');
        }

        final var pos = chars.position();

        final var minus = chars.test('-');

        if (chars.test(Character::isDigit, false)) {
            return parseInteger(minus);
        }

        if (minus) {
            chars.reset(pos);
        }

        for (final var entry : unaryOperations.entrySet()) {
            if (chars.test(entry.getKey())) {
                return entry.getValue().apply(parseElement());
            }
        }

        if (chars.test(Character::isLetter, false)) {
            return parseVariable();
        }

        throw new UnexpectedTokenException(chars.current(), chars.position());
    }

    private BiOperation parseOperation() {
        skipWhitespace();

        for (final var entry : binaryOperations.entrySet()) {
            if (chars.test(entry.getKey())) {
                return entry.getValue();
            }
        }

        throw new UnexpectedTokenException(chars.current(), chars.position());
    }

    private Const parseInteger(final boolean negative) {
        final int limit = negative ? Integer.MIN_VALUE : -Integer.MAX_VALUE;
        final int secondLimit = limit / 10;
        int result = 0;

        while (chars.test(Character::isDigit)) {
            final var digit = chars.current() - '0';

            if (result < secondLimit || (result *= 10) < limit + digit) {
                throw new OverflowException("Too long integer (only 32 bits are allowed)");
            }

            result -= digit;
        }

        return new Const(negative ? result : -result);
    }

    private Variable parseVariable() {
        final var name = new StringBuilder();

        while (chars.test(Character::isLetter)) {
            name.append(chars.current());
        }

        return new Variable(name.toString());
    }

    private void skipWhitespace() {
        //noinspection StatementWithEmptyBody
        while (chars.test(Character::isWhitespace)) {
        }
    }

    @FunctionalInterface
    interface Condition {
        boolean test();

        static boolean never() {
            return false;
        }
    }

    private static final Map<String, UnaryOperator<CommonExpression>> unaryOperations = Map.of(
        "-", Negate::new,
        "~", Not::new,
        "count", Count::new
    );

    private static final Map<String, BiOperation> binaryOperations = Map.of(
        "+", new BiOperation(Add::new, Precedence.ADD),
        "-", new BiOperation(Subtract::new, Precedence.ADD),
        "*", new BiOperation(Multiply::new, Precedence.MULTIPLY),
        "/", new BiOperation(Divide::new, Precedence.MULTIPLY),
        "&", new BiOperation(And::new, Precedence.AND),
        "^", new BiOperation(Xor::new, Precedence.XOR),
        "|", new BiOperation(Or::new, Precedence.OR)
    );

    private static class BiOperation implements PrecedenceAware, BinaryOperator<CommonExpression> {

        private final BinaryOperator<CommonExpression> delegate;
        private final Precedence precedence;

        public BiOperation(
            final BinaryOperator<CommonExpression> delegate,
            final Precedence precedence
        ) {
            this.delegate = delegate;
            this.precedence = precedence;
        }

        @Override
        public Precedence getPrecedence() {
            return precedence;
        }

        @Override
        public CommonExpression apply(final CommonExpression first, final CommonExpression second) {
            return delegate.apply(first, second);
        }
    }
}
