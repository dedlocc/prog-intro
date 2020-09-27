package sum;

public class Sum {
    public static void main(final String[] args) {
        int sum = 0;

        for (final var arg : args) {
            int lastSpace = 0, i = 0;
            for (final var end = arg.length(); i < end; ++i) {
                if (Character.isWhitespace(arg.charAt(i))) {
                    if (lastSpace < i) {
                        sum += Integer.parseInt(arg.substring(lastSpace, i));
                    }
                    lastSpace = i + 1;
                }
            }
            if (lastSpace < i) {
                sum += Integer.parseInt(arg.substring(lastSpace, i));
            }
        }

        System.out.println(sum);
    }
}
