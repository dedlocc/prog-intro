package reverse;

import common.FastScanner;

import java.io.IOException;

public class ReverseAvg {
    public static void main(final String[] args) throws IOException {
        final var matrix = new Matrix();
        final var rowSum = new LongArray();
        final var colSum = new LongArray();
        final var colSize = new IntArray();

        final var scanner = new FastScanner(System.in);

        var arr = new IntArray();
        long sum = 0;
        int i = 0;
        while (scanner.hasNext()) {
            if (!scanner.skipEmpty()) {
                final var n = scanner.nextInt();
                arr.add(n);
                sum += n;
                if (i == colSum.size()) {
                    colSum.add(n);
                    colSize.add(1);
                } else {
                    colSum.increase(i, n);
                    colSize.increase(i, 1);
                }
                ++i;
            }
            if (scanner.endOfLine()) {
                matrix.add(arr);
                rowSum.add(sum);
                arr = new IntArray();
                sum = 0;
                i = 0;
            }
        }

        for (i = 0; i < matrix.size(); ++i) {
            for (var j = 0; j < matrix.get(i).size(); ++j) {
                System.out.print((rowSum.get(i) + colSum.get(j) - matrix.get(i).get(j)) /
                        (colSize.get(j) + matrix.get(i).size() - 1) + " ");
            }
            System.out.println();
        }
    }
}
