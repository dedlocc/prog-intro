package expression.parser;

import expression.*;
import expression.binary.*;
import expression.exceptions.ParseException;
import expression.exceptions.UnexpectedCharacterException;
import expression.unary.Count;
import expression.unary.Negate;
import expression.unary.Not;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class ExpressionParser implements Parser {
    protected CharSource chars;

    protected final Map<String, BiOperation> binaryOperations;
    protected final Map<String, UnaryOperator<CommonExpression>> unaryOperations;

    protected ExpressionParser(
        final Map<String, BiOperation> binaryOperations,
        final Map<String, UnaryOperator<CommonExpression>> unaryOperations
    ) {
        this.binaryOperations = binaryOperations;
        this.unaryOperations = unaryOperations;
    }

    public ExpressionParser() {
        this(Map.of(
            "+", new BiOperation(Add::new, Precedence.ADD),
            "-", new BiOperation(Subtract::new, Precedence.ADD),
            "*", new BiOperation(Multiply::new, Precedence.MULTIPLY),
            "/", new BiOperation(Divide::new, Precedence.MULTIPLY),
            "&", new BiOperation(And::new, Precedence.AND),
            "^", new BiOperation(Xor::new, Precedence.XOR),
            "|", new BiOperation(Or::new, Precedence.OR)
        ), Map.of(
            "-", Negate::new,
            "~", Not::new,
            "count", Count::new
        ));
    }

    @Override
    public TripleExpression parse(final String expression) throws ParseException {
        chars = new CharSequenceSource(expression);
        return parseExpression(CharSource.END);
    }

    protected CommonExpression parseExpression(final char until) {
        final Deque<CommonExpression> elements = new ArrayDeque<>();
        final Deque<BiOperation> operations = new ArrayDeque<>();

        while (!test(until)) {
            if (!elements.isEmpty()) {
                final var operation = parseOperation();

                applyOperations(elements, operations, () -> operation.hasLowerPrecedence(operations.peek(), true));

                operations.push(operation);
            }

            elements.push(parseElement());
            skipWhitespace();
        }

        applyOperations(elements, operations, Condition::never);

        if (elements.isEmpty()) {
            throw new ParseException("Empty expression", "empty expression", chars.position());
        }

        return elements.pop();
    }

    protected static void applyOperations(
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

    protected BiOperation parseOperation() {
        skipWhitespace();

        for (final var entry : binaryOperations.entrySet()) {
            if (test(entry.getKey())) {
                return entry.getValue();
            }
        }

        throw UnexpectedCharacterException.fromCharSource(chars);
    }

    protected CommonExpression parseElement() {
        skipWhitespace();

        if (test('(')) {
            return parseExpression(')');
        }

        final var pos = chars.position();

        final var minus = test('-');

        if (test(Character::isDigit, false)) {
            return new Const(parseInteger(minus));
        }

        if (minus) {
            chars.reset(pos);
        }

        for (final var entry : unaryOperations.entrySet()) {
            final var op = entry.getKey();
            if (test(op)) {
                if (Character.isLetter(op.charAt(op.length() - 1)) && test(Character::isLetter, false)) {
                    chars.reset(pos);
                } else {
                    return entry.getValue().apply(parseElement());
                }
            }
        }

        if (test(Character::isLetter, false)) {
            return new Variable(parseVariableName());
        }

        throw UnexpectedCharacterException.fromCharSource(chars);
    }

    protected int parseInteger(final boolean negative) {
        final var sb = new StringBuilder();
        if (negative) {
            sb.append('-');
        }

        while (test(Character::isDigit)) {
            sb.append(chars.current());
        }

        return Integer.parseInt(sb.toString());
    }

    protected String parseVariableName() {
        final var name = new StringBuilder();

        while (test(Character::isLetter)) {
            name.append(chars.current());
        }

        return name.toString();
    }

    protected void skipWhitespace() {
        //noinspection StatementWithEmptyBody
        while (test(Character::isWhitespace)) {
        }
    }

    @FunctionalInterface
    protected interface Condition {
        boolean test();

        static boolean never() {
            return false;
        }
    }

    protected boolean test(final CharMatcher matcher, final boolean next) {
        if (matcher.match(chars.peek())) {
            if (next) {
                chars.next();
            }
            return true;
        }

        return false;
    }

    protected boolean test(final CharMatcher matcher) {
        return test(matcher, true);
    }

    protected boolean test(final char c) {
        return test(CharMatcher.equals(c));
    }

    protected boolean test(final CharSequence s) {
        final var position = chars.position();

        for (var i = 0; i < s.length(); ++i) {
            if (!test(s.charAt(i))) {
                chars.reset(position);
                return false;
            }
        }

        return true;
    }

    @FunctionalInterface
    protected interface CharMatcher {
        boolean match(final char c);

        static CharMatcher equals(final char ch) {
            return c -> c == ch;
        }
    }

    protected static class BiOperation implements PrecedenceAware, BinaryOperator<CommonExpression> {

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
