package classworks.cw11;

public interface CharSource {
    boolean hasNext();

    char nextChar();

    IllegalArgumentException error(final String message);
}
