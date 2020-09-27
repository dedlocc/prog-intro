package reverse;

import java.util.Scanner;

public class ReverseMin {

    public static void main(final String[] args) {
        var lines   = new IntArray();
        var rows    = new IntArray();
        var columns = new IntArray();

        final var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            final var line = scanner.nextLine();
            final var length = line.length();

            int ci = 0, min = Integer.MAX_VALUE;

            if (0 != length) {
                var start = 0;
                for (var i = 0; i <= length; ++i) {
                    if (length == i || ' ' == line.charAt(i)) {
                        var n = Integer.parseInt(line.substring(start, i));
                        if (n < min) {
                            min = n;
                        }
                        columns.setIf(ci++, n, v -> n < v);
                        start = i + 1;
                    }
                }
            }

            rows.add(min);
            lines.add(ci);
        }

        scanner.close();

        for (var i = 0; i < rows.size(); ++i) {
            for (var j = 0; j < lines.get(i); ++j) {
                System.out.print(Math.min(rows.get(i), columns.get(j)) + " ");
            }
            System.out.println();
        }
    }
}