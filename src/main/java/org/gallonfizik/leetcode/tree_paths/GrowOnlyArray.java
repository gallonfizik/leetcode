package org.gallonfizik.leetcode.tree_paths;

import java.util.Arrays;
import java.util.List;

class GrowOnlyArray<T> {
    private Object[] storage;
    private int size;

    public GrowOnlyArray() {
        size = 0;
        storage = new Object[10];
    }

    void push(T value) {
        if (size >= storage.length) {
            int nextLength = storage.length * 2;
            storage = Arrays.copyOf(storage, nextLength);
        }
        storage[size++] = value;
    }

    @SuppressWarnings("unchecked")
    List<T> toList() {
        return Arrays.asList((T[]) storage).subList(0, size);
    }

    void clear() {
        size = 0;
    }
}
