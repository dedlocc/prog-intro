package common;

import java.io.*;
import java.util.Arrays;

public final class FastScanner implements Closeable {
    private final Reader reader;
    private char[] buffer = new char[1024];
    private int pos = 0;
    private int end = 0;
    private boolean closed = false;
    private boolean needInput = false;
    private char lineBreak = 0;
    private DelimiterFormat format = Character::isWhitespace;

    public FastScanner(final Reader reader) {
        this.reader = reader;
    }

    public FastScanner(final InputStream source) {
        this(new InputStreamReader(source));
    }

    public FastScanner(final String source) {
        this(new StringReader(source));
    }

    public void setDelimiter(DelimiterFormat format) {
        this.format = format;
    }

    private void read() throws IOException {
        // If buffer is full make more space
        if (end == buffer.length) {
            if (pos > 0) {
                // Shift buffer to the left
                System.arraycopy(buffer, pos, buffer, 0, end - pos);
                end -= pos;
                pos = 0;
            } else {
                // Expand buffer
                buffer = Arrays.copyOf(buffer, 2 * buffer.length);
            }
        }

        final var n = reader.read(buffer, end, buffer.length - end);

        if (-1 == n) {
            closed = true;
        } else {
            end += n;
        }

        needInput = false;
    }

    private boolean isLineBreak(final char c) {
        return 0 == lineBreak ? '\n' == c || '\r' == c : lineBreak == c;
    }

    private void skipDelimiters() {
        while (pos < end && !isLineBreak(buffer[pos]) && format.isDelimiter(buffer[pos])) {
            ++pos;
        }
    }

    private String parseToken() {
        skipDelimiters();

        for (var i = pos; i <= end; ++i) {
            if (i == end && closed || i < end && format.isDelimiter(buffer[i])) {
                if (i < end && isLineBreak(buffer[i])) {
                    if (0 == lineBreak) {
                        lineBreak = buffer[i];
                    }
                }

                final var token = new String(buffer, pos, i - pos);

                if (i < end) {
                    pos = i + 1;
                } else {
                    pos = i;
                    if (!closed) {
                        // Token is found at the end of the buffer, so we need to read more
                        needInput = true;
                    }
                }

                return token;
            }
        }

        // If token is incomplete, more input is needed
        needInput = true;
        return null;
    }

    public boolean skipEmpty() {
        assert pos < end;
        final var empty = isLineBreak(buffer[pos]);
        if (empty) {
            ++pos;
        }
        return empty;
    }

    public boolean endOfLine() {
        return 0 < pos && isLineBreak(buffer[pos - 1]);
    }

    public void assertOpen() {
        assert !closed : "Scanner is closed";
    }

    public boolean hasNext() throws IOException {
        while (!closed) {
            skipDelimiters();
            if (pos != end) {
                return true;
            }
            read();
        }

        return false;
    }

    public String next() throws IOException {
        assertOpen();

        while (true) {
            final var token = parseToken();
            if (null != token) {
                return token;
            }

            assert needInput : "No more tokens";
            read();
        }
    }

    public int nextInt(int radix) throws IOException {
        return Integer.parseInt(next(), radix);
    }

    public int nextInt() throws IOException {
        return nextInt(10);
    }

    public int nextHexDec() throws IOException {
        String token = next();
        if (1 < token.length() && '0' == token.charAt(0) && 'x' == Character.toLowerCase(token.charAt(1))) {
            return Integer.parseUnsignedInt(token.substring(2), 16);
        }
        return Integer.parseInt(token);
    }

    @Override
    public void close() throws IOException {
        closed = true;
        this.reader.close();
    }

    @FunctionalInterface
    public interface DelimiterFormat {
        boolean isDelimiter(final char c);
    }
}
