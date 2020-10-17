package reverse;

import common.FastScanner;
import common.IntList;

import java.io.IOException;

public class ReverseHexDec {
    public static void main(final String[] args) {
        final var matrix = new IntList();
        final var lines = new IntList();

        final var scanner = new FastScanner(System.in);

        try {
            while (scanner.hasNext()) {
                if (!scanner.skipEmpty()) {
                    matrix.add(scanner.nextHexDec());
                }
                if (scanner.endOfLine()) {
                    lines.add(matrix.size() - 1);
                }
            }
        } catch (final IOException e) {
            System.out.println("Cannot read input: " + e.getMessage());
            return;
        }

        for (var i = lines.size() - 1; i >= 0; --i) {
            final var to = 0 == i ? 0 : lines.get(i - 1) + 1;
            for (var j = lines.get(i); j >= to; --j) {
                System.out.print(matrix.get(j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
