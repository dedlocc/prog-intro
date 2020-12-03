package expression.parser;

import expression.ExpressionException;

public class UnexpectedTokenException extends ExpressionException {
    UnexpectedTokenException(final char c, final int pos) {
        super(String.format("Unexpected token '%s' at position %d", c, pos), "unexpected token");
    }
}
