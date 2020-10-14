package classworks.cw6;

import java.awt.*;

public final class Rect extends AbstractShape {
    private final int w, h;


    public Rect(final Color color, final int x, final int y, final int width, final int height) {
        super(color, x, y);
        this.w = width;
        this.h = height;
    }

    @Override
    public void drawShape(final Graphics g) {
        g.fillRect(x, y, w, h);
    }

    @Override
    public Rect copy() {
        return new Rect(color, x, y, w, h);
    }
}
