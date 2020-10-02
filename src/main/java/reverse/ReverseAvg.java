package reverse;

import java.util.Scanner;

public class ReverseAvg {
    public static void main(final String[] args) {
        final var matrix = new Matrix();
        final var rowSum = new LongArray();
        final var colSum = new LongArray();
        final var colSize = new IntArray();

        final var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            // Using scanner (2x slower)
            final var sc = new Scanner(scanner.nextLine());
            final var arr = new IntArray();
            long sum = 0;
            for (var i = 0; sc.hasNextInt(); i++) {
                final var n = sc.nextInt();
                arr.add(n);
                sum += n;
                if (i == colSum.size()) {
                    colSum.add(n);
                    colSize.add(1);
                } else {
                    colSum.increase(i, n);
                    colSize.increase(i, 1);
                }
            }
            matrix.add(arr);
            rowSum.add(sum);
        }

        for (var i = 0; i < matrix.size(); ++i) {
            for (var j = 0; j < matrix.get(i).size(); ++j) {
                System.out.print((rowSum.get(i) + colSum.get(j) - matrix.get(i).get(j)) /
                        (colSize.get(j) + matrix.get(i).size() - 1) + " ");
            }
            System.out.println();
        }
    }
}
