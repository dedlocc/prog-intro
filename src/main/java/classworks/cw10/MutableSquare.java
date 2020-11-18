package classworks.cw10;

public class MutableSquare implements Square {
    private int x;
    private int y;
    private int side;

    public MutableSquare(final int x, final int y, final int side) {
        this.x = x;
        this.y = y;
        this.side = side;
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
        return side;
    }

    @Override
    public int getHeight() {
        return side;
    }

    @Override
    public int getSide() {
        return side;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setSide(final int side) {
        this.side = side;
    }
}
