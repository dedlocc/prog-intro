package game.logger;

public class SystemLogger extends StreamLogger {
    public SystemLogger() {
        super(System.out);
    }
}
