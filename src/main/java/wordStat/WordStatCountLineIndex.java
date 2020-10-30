package wordStat;

import common.FastScanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatCountLineIndex {
    private static boolean isDelimiter(final char c) {
        return !Character.isLetter(c) &&
            '\'' != c &&
            Character.DASH_PUNCTUATION != Character.getType(c);
    }

    public static void main(final String[] args) {
        if (2 > args.length) {
            System.err.println("Input and output file names must be specified as program arguments.");
            return;
        }

        final var map = new LinkedHashMap<String, WordStat>();

        try (
                final var file = new FileReader(args[0], StandardCharsets.UTF_8);
                final var scanner = new FastScanner(file)
        ) {
            scanner.setDelimiter(WordStatCountLineIndex::isDelimiter);
            int line = 1;
            int index = 1;
            while (scanner.hasNext()) {
                if (!scanner.skipEmpty()) {
                    String word = scanner.next().toLowerCase();
                    map.computeIfAbsent(word, w -> new WordStat()).add(line, index++);
                }
                if (scanner.endOfLine()) {
                    ++line;
                    index = 1;
                }
            }
        } catch (final FileNotFoundException e) {
            System.err.println("File not found: " + args[0]);
            return;
        } catch (final IOException e) {
            System.err.println("Cannot read from file " + args[0] + ": " + e.getMessage());
            return;
        }

        final var entries = new ArrayList<>(map.entrySet());
        map.clear(); // Clear memory
        entries.sort(Map.Entry.comparingByValue());

        try (final var writer = new FileWriter(args[1], StandardCharsets.UTF_8)) {
            for (final var entry : entries) {
                writer.append(entry.getKey()).append(entry.getValue().str()).append('\n');
            }
        } catch (final IOException e) {
            System.err.println("Cannot write to file " + args[1] + ": " + e.getMessage());
        }
    }
}
