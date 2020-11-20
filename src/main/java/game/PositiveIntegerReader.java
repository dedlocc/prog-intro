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

    public int read() {
        if (in.hasNextInt()) {
            final var input = in.nextInt();
            if (input > 0) {
                return input;
            }
        } else {
            in.next();
        }

        return -1;
    }
}
