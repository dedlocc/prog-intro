package classworks.cw6;

import java.awt.*;

public final class Circle extends AbstractShape {
    private final int r;

    public Circle(final Color color, final int x, final int y, final int r) {
        super(color, x, y);
        this.r = r;
    }

    @Override
    public void drawShape(final Graphics g) {
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

    @Override
    public void shift(final int dx, final int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public Circle copy() {
        return new Circle(color, x, y, r);
    }
}
