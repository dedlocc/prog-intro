package expression.exceptions;

public class UnknownVariableException extends ParseException {
    public UnknownVariableException(final String name, final int pos) {
        super(String.format("Unknown variable name '%s'", name), "unknown variable", pos);
    }
}
