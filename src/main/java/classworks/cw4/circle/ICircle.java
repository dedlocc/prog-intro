package classworks.cw4.circle;

import classworks.cw4.vector.IVector;

public final class ICircle {
    private final IVector center;
    private final double radius;

    public ICircle(IVector center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public IVector getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public ICircle shift(IVector v) {
        return new ICircle(center.add(v), radius);
    }

    @Override
    public String toString() {
        return "ICircle{" + center + ", " + radius + '}';
    }
}
