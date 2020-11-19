package game.logger;

public interface Logger {
    void log(final String msg);

    default <T> void log(final T obj) {
        log(String.valueOf(obj));
    }
}
