package common;

import java.util.Arrays;

public final class IntList {
    public static final int DEFAULT_CAPACITY = 8192;
    private int[] values = new int[DEFAULT_CAPACITY];
    private int size = 0;

    public int size() {
        return size;
    }

    public int get(int i) {
        return values[i];
    }

    public void add(int n) {
        if (values.length == size) {
            values = Arrays.copyOf(values, 2 * values.length);
        }
        values[size++] = n;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(values, size));
    }
}