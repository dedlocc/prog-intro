package md2html;

import expression.parser.CharSequenceSource;
import expression.parser.CharSource;

import java.util.Iterator;

public class ParagraphSource implements CharSource {
    private final Iterator<String> lines;
    private CharSource source;

    public ParagraphSource(final Iterator<String> lines) {
        this.lines = lines;
    }

    public boolean nextParagraph() {
        if (!lines.hasNext()) {
            return false;
        }

        var line = lines.next();

        while (line.isEmpty()) {
            if (!lines.hasNext()) {
                return false;
            }
            line = lines.next();
        }

        final var sb = new StringBuilder(line);

        while (lines.hasNext()) {
            line = lines.next();
            if (line.isEmpty()) {
                break;
            }
            sb.append(System.lineSeparator()).append(line);
        }

        source = new CharSequenceSource(sb.toString());
        return true;
    }

    @Override
    public char next() {
        return source.next();
    }

    @Override
    public char current() {
        return source.current();
    }

    @Override
    public char peek() {
        return source.peek();
    }

    @Override
    public int position() {
        return source.position();
    }

    @Override
    public void reset(final int position) {
        source.reset(position);
    }
}
