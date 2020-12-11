package expression.parser;

public interface CharSource {
    char END = '\uffff';

    char next();

    char current();

    char peek();

    int position();

    void reset(final int position);
}
