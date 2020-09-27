package sum;

public class SumLong {
    public static void main(final String[] args) {
        long sum = 0;

        for (final var arg : args) {
            int lastSpace = 0, i = 0;
            for (final var end = arg.length(); i < end; ++i) {
                if (Character.isWhitespace(arg.charAt(i))) {
                    if (lastSpace < i) {
                        sum += Long.parseLong(arg.substring(lastSpace, i));
                    }
                    lastSpace = i + 1;
                }
            }
            if (lastSpace < i) {
                sum += Long.parseLong(arg.substring(lastSpace, i));
            }
        }

        System.out.println(sum);
    }
}
