package expression.parser;

import expression.*;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public final class ExpressionParser implements Parser {
    private CharacterIterator chars;

    @Override
    public CommonExpression parse(final String expression) throws ParseException {
        chars = new StringCharacterIterator(expression);
        return parseExpression(CharacterIterator.DONE);
    }

    private CommonExpression parseExpression(final char until) {
        final Deque<CommonExpression> elements = new ArrayDeque<>();
        final Deque<BiOperation> operations = new ArrayDeque<>();

        elements.push(parseElement());
        skipWhitespace();

        while (until != chars.current()) {
            final var operation = parseOperation();

            if (!operations.isEmpty() && 0 >= operation.precedence.compareTo(operations.peek().precedence)) {
                assert elements.size() == operations.size() + 1;

                var element = elements.pop();

                while (!elements.isEmpty() && !operation.hasLowerPrecedence(operations.peek(), true)) {
                    element = operations.pop().apply(elements.pop(), element);
                }

                elements.push(element);
            }

            operations.push(operation);
            elements.push(parseElement());

            skipWhitespace();
        }

        applyOperations(elements, operations);

        return elements.pop();
    }

    private static void applyOperations(final Deque<CommonExpression> elements, final Deque<BiOperation> operations) {
        assert elements.size() == operations.size() + 1;

        var element = elements.pop();

        while (!elements.isEmpty()) {
            element = operations.pop().apply(elements.pop(), element);
        }

        elements.push(element);
    }

    private CommonExpression parseElement() {
        skipWhitespace();

        if (match('(')) {
            final var e = parseExpression(')');
            chars.next();
            return e;
        }

        final var index = chars.getIndex();

        final var minus = match('-');

        if (match(Character::isDigit, false)) {
            return parseInteger(minus);
        }

        if (minus) {
            chars.setIndex(index);
        }

        for (final var entry : unaryOperations.entrySet()) {
            if (match(entry.getKey())) {
                return entry.getValue().apply(parseElement());
            }
            chars.setIndex(index);
        }

        if (match(Character::isLetter, false)) {
            return parseVariable();
        }

        throw new ParseException(
            String.format("Unexpected token '%c' at position %d", chars.current(), chars.getIndex()));
    }

    private BiOperation parseOperation() {
        skipWhitespace();

        final var index = chars.getIndex();

        for (final var entry : binaryOperations.entrySet()) {
            if (match(entry.getKey())) {
                return entry.getValue();
            }
            chars.setIndex(index);
        }

        throw new ParseException(String
            .format("Unexpected token '%s' at position %d", chars.current(), chars.getIndex()));
    }

    private Const parseInteger(final boolean negative) {
        final int limit = negative ? Integer.MIN_VALUE : -Integer.MAX_VALUE;
        long result = 0;

        while (Character.isDigit(chars.current())) {
            result *= 10;
            result -= chars.current() - '0';
            chars.next();

            if (result < limit) {
                throw new IntegerParseException("Integer overflow");
            }
        }

        return new Const(negative ? result : -result);
    }

    private Variable parseVariable() throws VariableParseException {
        final var name = new StringBuilder();

        while (Character.isLetter(chars.current())) {
            name.append(chars.current());
            chars.next();
        }

        return new Variable(name.toString());
    }

    private void skipWhitespace() {
        //noinspection StatementWithEmptyBody
        while (match(Character::isWhitespace)) {
        }
    }

    private boolean match(final CharMatcher matcher, final boolean nextIfMatches) {
        if (matcher.match(chars.current())) {
            if (nextIfMatches) {
                chars.next();
            }
            return true;
        }
        return false;
    }

    private boolean match(final CharMatcher matcher) {
        return match(matcher, true);
    }

    private boolean match(final char expected, final boolean nextIfMatches) {
        return match(c -> c == expected, nextIfMatches);
    }

    private boolean match(final char expected) {
        return match(c -> c == expected);
    }

    private boolean match(final CharSequence expected) {
        for (var i = 0; i < expected.length(); ++i) {
            if (!match(expected.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @FunctionalInterface
    interface CharMatcher {
        boolean match(char c);
    }

    private static final Map<String, UnaryOperator<CommonExpression>> unaryOperations = Map.of(
        "-", Negate::new,
        "abs", Abs::new,
        "square", Square::new,
        "reverse", Reverse::new,
        "digits", Digits::new
    );

    private static final Map<String, BiOperation> binaryOperations = Map.of(
        "+", new BiOperation(Add::new, Precedence.ADD),
        "-", new BiOperation(Subtract::new, Precedence.ADD),
        "*", new BiOperation(Multiply::new, Precedence.MULTIPLY),
        "/", new BiOperation(Divide::new, Precedence.MULTIPLY),
        "&", new BiOperation(And::new, Precedence.AND),
        "^", new BiOperation(Xor::new, Precedence.XOR),
        "|", new BiOperation(Or::new, Precedence.OR),
        "<<", new BiOperation(LeftShift::new, Precedence.SHIFT),
        ">>", new BiOperation(RightShift::new, Precedence.SHIFT)
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
