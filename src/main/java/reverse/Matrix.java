package reverse;

import java.util.Arrays;

/**
 * @author Rahimjon Hakimov
 */
public final class Matrix {
    private IntArray[] values = new IntArray[IntArray.DEFAULT_SIZE];
    private int size = 0;

    private void expand() {
        values = Arrays.copyOf(values, values.length << 1);
    }

    public int size() {
        return size;
    }

    public IntArray get(int i) {
        assert i < size && 0 <= i;
        return values[i];
    }

    public void add(IntArray v) {
        assert values.length >= size;
        if (values.length == size) {
            expand();
        }
        values[size++] = v;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(values, size));
    }
}