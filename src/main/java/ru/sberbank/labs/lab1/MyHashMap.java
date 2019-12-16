package ru.sberbank.labs.lab1;

public class MyHashMap implements IntMap{
    private static int size = 0;
    private void addSize() {
        this.size++;
    }
    @Override
    public int size(){return this.size;}
    @Override
    public boolean isEmpty() {
        if (this.size() > 0)
            return false;
        return true;
    }
}
