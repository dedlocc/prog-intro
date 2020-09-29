package classworks.cw4.vector;

public final class MVector {
    private double x;
    private double y;

    public MVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public MVector(double x) {
        this(x, 0);
    }

    public MVector() {
        this(0, 0);
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

    public void add(MVector v) {
        x += v.x;
        y += v.y;
    }

    public MVector copy() {
        return new MVector(x, y);
    }
}
