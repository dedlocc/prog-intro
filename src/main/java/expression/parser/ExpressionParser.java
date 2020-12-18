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

        while (!chars.test(until)) {
            if (!elements.isEmpty()) {
                final var operation = parseBinaryOperation();

                applyOperations(elements, operations, () -> operation.hasLowerPrecedence(operations.peek(), true));

                operations.push(operation);
            }

            elements.push(parseElement());
            skipWhitespace();
        }

        applyOperations(elements, operations, Condition::never);

        if (elements.isEmpty()) {
            throw new ParseException("Empty expression", chars.position());
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

    protected BiOperation parseBinaryOperation() {
        skipWhitespace();

        final var op = parseRawOperation(binaryOperations);
        if (null != op) {
            return op;
        }

        throw UnexpectedCharacterException.fromCharSource(chars);
    }

    protected CommonExpression parseElement() {
        skipWhitespace();

        if (chars.test('(')) {
            return parseExpression(')');
        }

        final var pos = chars.position();

        final var minus = chars.test('-');

        if (chars.test(Character::isDigit, false)) {
            return new Const(parseInteger(minus));
        }

        if (minus) {
            chars.reset(pos);
        }

        final var op = parseRawOperation(unaryOperations);
        if (null != op) {
            return op.apply(parseElement());
        }

        if (chars.test(Character::isLetter, false)) {
            return new Variable(parseVariableName());
        }

        throw UnexpectedCharacterException.fromCharSource(chars);
    }

    protected <T> T parseRawOperation(final Map<String, T> dict) {
        final var isAlphabetical = chars.test(Character::isLetter, false);
        final CharMatcher condition = isAlphabetical ? ExpressionParser::isAlphaNumeric : ExpressionParser::isSimple;
        final var pos = chars.position();
        final var sb = new StringBuilder();

        while (chars.test(condition)) {
            sb.append(chars.current());
        }

        if (isAlphabetical) {
            final var op = sb.toString();
            if (dict.containsKey(op)) {
                return dict.get(op);
            }
            chars.reset(pos);
            return null;
        }

        //noinspection SizeReplaceableByIsEmpty
        while (0 < sb.length()) {
            final var op = sb.toString();
            if (dict.containsKey(op)) {
                chars.reset(pos + sb.length());
                return dict.get(op);
            }
            sb.setLength(sb.length() - 1);
        }

        chars.reset(pos);
        return null;
    }

    protected int parseInteger(final boolean negative) {
        final var sb = new StringBuilder();
        if (negative) {
            sb.append('-');
        }

        while (chars.test(Character::isDigit)) {
            sb.append(chars.current());
        }

        return Integer.parseInt(sb.toString());
    }

    protected String parseVariableName() {
        final var name = new StringBuilder();

        while (chars.test(Character::isLetter)) {
            name.append(chars.current());
        }

        return name.toString();
    }

    protected void skipWhitespace() {
        //noinspection StatementWithEmptyBody
        while (chars.test(Character::isWhitespace)) {
        }
    }

    @FunctionalInterface
    protected interface Condition {
        boolean test();

        static boolean never() {
            return false;
        }
    }

    static boolean isAlphaNumeric(final char ch) {
        return Character.isDigit(ch) || Character.isLetter(ch);
    }

    static boolean isSimple(final char ch) {
        return CharSource.END != ch && '(' != ch && !Character.isWhitespace(ch) && !isAlphaNumeric(ch);
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
