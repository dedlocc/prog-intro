package classworks.cw6;

import java.awt.*;

public final class Line extends AbstractShape {
    private int x2, y2;

    public Line(final Color color, final int x1, final int y1, final int x2, final int y2) {
        super(color, x1, y1);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void drawShape(Graphics g) {
        g.drawLine(x, y, x2, y2);
    }

    @Override
    public void shift(final int dx, final int dy) {
        super.shift(dx, dy);
        x2 += dx;
        y2 += dx;
    }

    @Override
    public Line copy() {
        return new Line(color, x, y, x2, y2);
    }
}
