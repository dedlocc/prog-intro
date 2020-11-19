package game.logger;

public final class DummyLogger implements Logger {
    @Override
    public void log(final String msg) {
        // Ignore message
    }

    @Override
    public <T> void log(final T obj) {
        // Ignore message
    }
}
