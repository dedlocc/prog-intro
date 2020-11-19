package game.logger;

import java.io.PrintStream;

public class StreamLogger implements Logger {
    private final PrintStream stream;

    public StreamLogger(final PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void log(final String msg) {
        stream.println(msg);
    }
}
