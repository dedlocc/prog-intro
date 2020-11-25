package classworks.cw11;

public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char nextChar() {
        return data.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(final String message) {
        throw new IllegalArgumentException(String.format("%d: %s", pos, message));
    }
}
