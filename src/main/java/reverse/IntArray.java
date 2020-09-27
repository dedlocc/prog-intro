package reverse;

import java.util.Arrays;
import java.util.function.IntPredicate;

public final class IntArray {
    public static final int DEFAULT_SIZE = 16;
    private int[] values = new int[DEFAULT_SIZE];
    private int size = 0;

    private void expand() {
        values = Arrays.copyOf(values, values.length << 1);
    }

    public int size() {
        return size;
    }

    public int get(int i) {
        return values[i];
    }

    public void increase(int i, int n) {
        values[i] += n;
    }

    public void add(int n) {
        if (values.length == size) {
            expand();
        }
        values[size++] = n;
    }

    public void setIf(int i, int n, IntPredicate condition) {
        if (size == i) {
            add(n);
        } else if (condition.test(values[i])) {
            values[i] = n;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(values, size));
    }
}