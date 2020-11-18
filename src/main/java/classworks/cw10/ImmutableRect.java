package classworks.cw10;

public class ImmutableRect implements Rect {
    protected final int x;
    protected final int y;
    protected final int w;
    protected final int h;

    public ImmutableRect(final int x, final int y, final int w, final int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }
}
