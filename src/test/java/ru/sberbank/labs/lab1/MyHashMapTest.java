package ru.sberbank.labs.lab1;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
public class MyHashMapTest {
    @Test
    public void size() {
        MyHashMap<Integer> map = new MyHashMap<>();
        for(int i = 0; i < 1000; i++) {
            assertEquals(i, map.size());
            map.put(i, i);
        }
    }

    @Test
    public void isEmpty() {
        MyHashMap<Integer> map = new MyHashMap<>();
        assertTrue(map.isEmpty());
        map.put(1,1);
        assertFalse(map.isEmpty());
        map.remove(1);
        assertTrue(map.isEmpty());
    }

    @Test
    public void containsKey() {
        MyHashMap<Integer> map = new MyHashMap<>();
        assertFalse(map.containsKey(1));
        for (int i = 0; i < 10000; i++){
            map.put(i, i);
        }
        for (int i = 0; i < 10000; i++){
            assertTrue(map.containsKey(i));
        }
        for (int i = 10001; i < 20000; i++){
            assertFalse(map.containsKey(i));
        }
    }

    @Test
    public void containsValue() {
        MyHashMap<Integer> map = new MyHashMap<>();
        assertFalse(map.containsValue(1));
        for (int i = 0; i < 10000; i++){
            map.put(i, i);
        }
        for (int i = 0; i < 10000; i++){
            assertTrue(map.containsValue(i));
        }
        for (int i = 10001; i < 20000; i++){
            assertFalse(map.containsValue(i));
        }
    }

    @Test
    public void entrySet() {
        int size = 20;
        MyHashMap<Person> myHashMap = new MyHashMap<>();
        Person[] persons = EntryPoint.personsGenerator(size);
        Integer[] digs = EntryPoint.integerGenerator(size);
        Set<IntEntry<Person>> entrySet = new HashSet<IntEntry<Person>>();
        for (int i = 0; i < size; i++){
            entrySet.add(new IntEntry<>(digs[i], persons[i]));
            myHashMap.put(digs[i], persons[i]);
        }
        assertTrue(entrySet.containsAll(myHashMap.entrySet()));
    }
}
