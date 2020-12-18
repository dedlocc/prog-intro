package md2html;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class Md2Html {
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    public static void main(final String[] args) {
        final String html;

        try (
            final var fileReader = new FileReader(args[0], CHARSET);
            final var bufferedReader = new BufferedReader(fileReader)
        ) {
            html = new Parser().parse(new ParagraphSource(bufferedReader.lines().iterator()));
        } catch (final IOException e) {
            System.out.println("Cannot read from file '" + args[0] + "': " + e.getMessage());
            return;
        }

        try (final var out = new FileWriter(args[1], CHARSET)) {
            out.write(html);
        } catch (final IOException e) {
            System.out.println("Cannot write to file '" + args[1] + "': " + e.getMessage());
        }
    }
}
