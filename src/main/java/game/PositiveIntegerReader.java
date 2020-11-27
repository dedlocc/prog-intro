package game;

import java.util.Scanner;

public class PositiveIntegerReader {
    private final Scanner in;

    public PositiveIntegerReader(final Scanner in) {
        this.in = in;
    }

    public PositiveIntegerReader() {
        this(new Scanner(System.in));
    }

    public int read(final boolean allowZero) {
        if (in.hasNextInt()) {
            final var input = in.nextInt();
            if (0 < input || allowZero && 0 == input) {
                return input;
            }
        } else {
            in.next();
        }

        return -1;
    }

    public int read() {
        return read(false);
    }
}
