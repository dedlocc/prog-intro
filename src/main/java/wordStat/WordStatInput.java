package wordStat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public final class WordStatInput {
    private static final int BUFFER_SIZE = 512;

    private static void addWord(final String word, final Map<String, Integer> map) {
        map.compute(word, (a, b) -> null == b ? 1 : 1 + b);
    }

    public static void main(final String[] args) {
        if (2 > args.length) {
            System.err.println("Input and output file names must be specified as program arguments.");
            return;
        }

        var map = new LinkedHashMap<String, Integer>();

        try (var reader = new FileReader(args[0], StandardCharsets.UTF_8)) {
            final var buffer = new char[BUFFER_SIZE];
            final var remainder = new StringBuilder();
            while (true) {
                final var length = reader.read(buffer);
                if (-1 == length) {
                    break;
                }
                int start = 0;
                for (var i = 0; i < length; ++i) {
                    final var type = Character.getType(buffer[i]);
                    if (Character.UPPERCASE_LETTER == type) {
                        buffer[i] = Character.toLowerCase(buffer[i]);
                    } else if (Character.LOWERCASE_LETTER != type && Character.DASH_PUNCTUATION != type && '\'' != buffer[i]) {
                        if (0 != remainder.length()) {
                            addWord(remainder.append(buffer, start, i - start).toString(), map);
                            remainder.setLength(0);
                        } else if (start < i) {
                            addWord(new String(buffer, start, i - start), map);
                        }
                        start = i + 1;
                    }
                }
                remainder.append(buffer, start, length - start);
            }
            if (0 != remainder.length()) {
                addWord(remainder.toString(), map);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + args[0]);
            return;
        } catch (IOException e) {
            System.err.println("Cannot read from file " + args[0] + ": " + e.getMessage());
            return;
        }

        var sb = new StringBuilder();
        for (var entry : map.entrySet()) {
            sb.append(entry.getKey()).append(' ').append(entry.getValue()).append('\n');
        }

        try (var writer = new FileWriter(args[1], StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.err.println("Cannot write to file " + args[0] + ": " + e.getMessage());
        }
    }
}
