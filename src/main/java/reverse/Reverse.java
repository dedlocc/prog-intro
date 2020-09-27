package reverse;

import java.util.Scanner;

public class Reverse {
    public static void main(final String[] args) {
        final var matrix = new IntArray();
        final var lines = new IntArray();

        final var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            final var line = scanner.nextLine();
            final var length = line.length();
            if (0 != length) {
                var start = 0;
                for (var i = 0; i <= length; ++i) {
                    if (length == i || ' ' == line.charAt(i)) {
                        matrix.add(Integer.parseInt(line.substring(start, i)));
                        start = i + 1;
                    }
                }
            }
            lines.add(matrix.size() - 1);
        }

        scanner.close();

        for (var i = lines.size() - 1; i >= 0; --i) {
            final var to = 0 == i ? 0 : lines.get(i - 1) + 1;
            for (var j = lines.get(i); j >= to; --j) {
                System.out.print(matrix.get(j) + " ");
            }
            System.out.println();
        }
    }
}
