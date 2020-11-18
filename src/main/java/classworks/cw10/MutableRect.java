package classworks.cw10;

public class MutableRect implements Rect {
    protected int x;
    protected int y;
    protected int w;
    protected int h;

    public MutableRect(final int x, final int y, final int w, final int h) {
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

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setWidth(final int w) {
        this.w = w;
    }

    public void setHeight(final int h) {
        this.h = h;
    }
}
