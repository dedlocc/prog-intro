package reverse;

import java.util.Arrays;

public final class Matrix {
    private IntArray[] values = new IntArray[IntArray.DEFAULT_CAPACITY];
    private int size = 0;

    public int size() {
        return size;
    }

    public IntArray get(int i) {
        return values[i];
    }

    public void add(IntArray v) {
        if (values.length == size) {
            values = Arrays.copyOf(values, 2 * values.length);
        }
        values[size++] = v;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(values, size));
    }
}