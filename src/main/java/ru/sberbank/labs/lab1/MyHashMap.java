package ru.sberbank.labs.lab1;

import java.util.Set;

public class MyHashMap implements IntMap{
    private static int size = 0;
    private void addSize() {
        this.size++;
    }

    @Override
    public Object get(int i) {
        return null;
    }

    @Override
    public Object put(int key, Object value) {
        return null;
    }

    @Override
    public Object remove(int i) {
        return null;
    }

    @Override
    public int size(){return this.size;}
    @Override
    public boolean isEmpty() {
        if (this.size() > 0)
            return false;
        return true;
    }

    @Override
    public boolean containsKey(int i) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<IntEntry<Person>> entrySet() {
        return null;
    }
}
