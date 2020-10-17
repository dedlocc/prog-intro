package reverse;

import java.util.Arrays;
import java.util.function.IntPredicate;

public final class IntArray {
    public static final int DEFAULT_CAPACITY = 8192;
    private int[] values = new int[DEFAULT_CAPACITY];
    private int size = 0;

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
            values = Arrays.copyOf(values, 2 * values.length);
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