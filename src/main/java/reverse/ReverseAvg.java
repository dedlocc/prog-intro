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
            final var line = scanner.nextLine();
            final var length = line.length();

            final var arr = new IntArray();
            long sum = 0;

            if (0 != length) {
                var start = 0;
                var j = 0;
                for (var i = 0; i <= length; ++i) {
                    if (length == i || ' ' == line.charAt(i)) {
                        final var n = Integer.parseInt(line.substring(start, i));
                        arr.add(n);
                        sum += n;
                        if (j == colSum.size()) {
                            colSum.add(n);
                            colSize.add(1);
                        } else {
                            colSum.increase(j, n);
                            colSize.increase(j, 1);
                        }
                        ++j;
                        start = i + 1;
                    }
                }
            }
            matrix.add(arr);
            rowSum.add(sum);

//            // Using scanner (2x slower)
//            final var sc = new Scanner(scanner.nextLine());
//            final var arr = new IntArray();
//            long sum = 0;
//            for (var i = 0; sc.hasNextInt(); ++i) {
//                final var n = sc.nextInt();
//                arr.add(n);
//                sum += n;
//                if (i == colSum.size()) {
//                    colSum.add(n);
//                    colSize.add(1);
//                } else {
//                    colSum.increase(i, n);
//                    colSize.increase(i, 1);
//                }
//            }
//            matrix.add(arr);
//            rowSum.add(sum);
        }

        scanner.close();


        for (var i = 0; i < matrix.size(); ++i) {
            for (var j = 0; j < matrix.get(i).size(); ++j) {
                System.out.print((rowSum.get(i) + colSum.get(j) - matrix.get(i).get(j)) /
                        (colSize.get(j) + matrix.get(i).size() - 1) + " ");
            }
            System.out.println();
        }
    }
}
