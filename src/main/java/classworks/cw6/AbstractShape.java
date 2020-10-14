package classworks.cw6;

import java.awt.*;

public abstract class AbstractShape implements Shape {
    protected Color color;
    protected int x, y;

    public AbstractShape(final Color color, final int x, final int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void draw(final Graphics g) {
        final Color oldColor = g.getColor();
        g.setColor(color);
        drawShape(g);
        g.setColor(oldColor);
    }

    protected abstract void drawShape(final Graphics g);

    @Override
    public void shift(final int dx, final int dy) {
        x += dx;
        y += dy;
    }
}
