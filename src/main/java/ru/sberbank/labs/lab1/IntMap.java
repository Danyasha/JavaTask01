package ru.sberbank.labs.lab1;

import java.util.Set;

public interface IntMap<T> {
    T get(int i);
    T put(int key, T value);
    T remove(int i);
    int size();
    boolean isEmpty();
    boolean containsKey(int i);
    boolean containsValue(T o);
    void clear();
    Set<IntEntry<Person>> entrySet();
}
