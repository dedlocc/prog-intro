package reverse;

import java.util.Arrays;
import java.util.function.IntPredicate;

public final class LongArray {
    private long[] values = new long[IntArray.DEFAULT_CAPACITY];
    private int size = 0;

    private void expand() {
        values = Arrays.copyOf(values, values.length << 1);
    }

    public int size() {
        return size;
    }

    public long get(int i) {
        return values[i];
    }

    public void increase(int i, int n) {
        values[i] += n;
    }

    public void add(long n) {
        if (values.length == size) {
            expand();
        }
        values[size++] = n;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(values, size));
    }
}