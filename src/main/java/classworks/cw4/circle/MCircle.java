package classworks.cw4.circle;

import classworks.cw4.vector.MVector;

public final class MCircle {
    private final MVector center;
    private final double radius;

    public MCircle(MVector center, double radius) {
        this.center = center.copy();
        this.radius = radius;
    }

    public MVector getCenter() {
        return center.copy();
    }

    public double getRadius() {
        return radius;
    }

    public void shift(MVector v) {
        center.add(v);
    }

    public MCircle copy() {
        return new MCircle(center, radius);
    }

    @Override
    public String toString() {
        return "ICircle{" + center + ", " + radius + '}';
    }
}
