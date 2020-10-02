package sum;

public class SumLongSpace {
    public static void main(final String[] args) {
        long sum = 0;

        for (final var arg : args) {
            int lastSpace = 0, i = 0;
            for (final var end = arg.length(); i <= end; i++) {
                // Compare with end each iteration - slower than just repeating the code once afterwards
                if (end == i || Character.SPACE_SEPARATOR == Character.getType(arg.charAt(i))) {
                    if (lastSpace < i) {
                        sum += Long.parseLong(arg.substring(lastSpace, i));
                    }
                    lastSpace = i + 1;
                }
            }
        }

        System.out.println(sum);
    }
}
