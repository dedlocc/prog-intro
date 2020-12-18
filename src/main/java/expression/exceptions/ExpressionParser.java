package expression.exceptions;

import expression.Precedence;

import java.util.Map;
import java.util.Set;

public class ExpressionParser extends expression.parser.ExpressionParser implements Parser {
    private static final Set<String> allowedVariableNames = Set.of("x", "y", "z");

    public ExpressionParser() {
        super(Map.of(
            "+", new BiOperation(CheckedAdd::new, Precedence.ADD),
            "-", new BiOperation(CheckedSubtract::new, Precedence.ADD),
            "*", new BiOperation(CheckedMultiply::new, Precedence.MULTIPLY),
            "/", new BiOperation(CheckedDivide::new, Precedence.MULTIPLY),
            "min", new BiOperation(CheckedMin::new, Precedence.MIN_MAX),
            "max", new BiOperation(CheckedMax::new, Precedence.MIN_MAX)
        ), Map.of(
            "-", CheckedNegate::new,
            "abs", CheckedAbs::new,
            "sqrt", CheckedSqrt::new
        ));
    }

    @Override
    protected int parseInteger(final boolean negative) {
        final var startPos = chars.position();

        try {
            return super.parseInteger(negative);
        } catch (final NumberFormatException e) {
            throw new ConstOverflowException(negative, negative ? startPos - 1 : startPos, e);
        }
    }

    @Override
    protected String parseVariableName() {
        final var startPos = chars.position();
        final var name = super.parseVariableName();

        if (!allowedVariableNames.contains(name)) {
            throw new UnknownVariableException(name, startPos);
        }

        return name;
    }
}
