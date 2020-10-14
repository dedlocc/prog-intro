package classworks.cw6;

import java.awt.*;

public interface Shape extends Copyable {
    void draw(Graphics g);

    void shift(final int dx, final int dy);

    void setColor(Color color);

    Color getColor();

    @Override
    Shape copy();
}
