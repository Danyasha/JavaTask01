package ru.sberbank.labs.lab1;

import java.util.Objects;

public final class IntEntry<T extends Comparable> implements Comparable<IntEntry<T>> {
    private final int key;
    private final T value;

    public IntEntry(int key, T value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntEntry<?> intEntry = (IntEntry<?>) o;
        return key == intEntry.key && Objects.equals(value, intEntry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }


    @Override
    public int compareTo(IntEntry<T> tIntEntry) {
        int first = this.key - tIntEntry.key;
        if (first == 0) {
            first = this.value.compareTo(tIntEntry.value);
        }
        return first;
    }
}
