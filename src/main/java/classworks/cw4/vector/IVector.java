package classworks.cw4.vector;

public final class IVector {
    public static int c;
    private final double x;
    private final double y;

    private IVector(double x, double y) {
        this.x = x;
        this.y = y;
        ++c;
    }

    public double getR() {
        return Math.hypot(x, y);
    }

    public double getPhi() {
        return Math.atan2(y, x);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + x + ", " + y + '}';
    }

    public IVector add(IVector v) {
        return new IVector(x + v.x, y + v.y);
    }

    public static IVector fromCartesian(double x, double y) {
        return new IVector(x, y);
    }

    public static IVector fromPolar(double r, double phi) {
        return new IVector(Math.cos(phi) * r, Math.sin(phi) * r);
    }
}
