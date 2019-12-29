package ru.sberbank.labs.lab1;

import java.util.*;

public class MyHashMap<V extends Comparable> implements IntMap<V>{
    private int capacity = 16;
    private int size = 0;
    // TODO здесь неэффективно использовать ArrayList, попробуй Array
    private LinkedList<IntEntry<V>>[] arrayBuckets;
    private List<LinkedList<IntEntry<V>>> buckets;
    private double loadFactor = 0.75;

    private void resize(){
        int newCapacity = this.capacity * 2;
        List<LinkedList<IntEntry<V>>> newBuckets = new ArrayList<>(newCapacity);
        @SuppressWarnings("unchecked")
        LinkedList<IntEntry<V>>[] newArrayBuckets = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++){
            newBuckets.add(new LinkedList<IntEntry<V>>());
        }
        for(LinkedList<IntEntry<V>> tempBucket: this.buckets){
            for(IntEntry<V> tempElem: tempBucket){
                int index = tempElem.getKey() & (newCapacity - 1);
                newBuckets.get(index).add(tempElem);
            }
        }
        this.buckets = newBuckets;
        this.capacity = newCapacity;
    }
    private void addSize(){
        if (this.size + 1 >= this.capacity * this.loadFactor){
            this.resize();
        }
        this.size++;
    }
    private int getIndex(int hash){return hash & (this.capacity - 1);}
    public MyHashMap(){
        this.buckets = new ArrayList<LinkedList<IntEntry<V>>>(this.capacity);
        for (int i = 0; i < this.capacity; i++){
            this.buckets.add(new LinkedList<IntEntry<V>>());
        }
    }
    public MyHashMap(int capacity){
        this.capacity = capacity;
        this.buckets = new ArrayList<LinkedList<IntEntry<V>>>(this.capacity);
        for (int i = 0; i < this.capacity; i++){
            this.buckets.add(new LinkedList<IntEntry<V>>());
        }
    }
    public MyHashMap(int capacity, double loadFactor){
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.buckets = new ArrayList<LinkedList<IntEntry<V>>>(this.capacity);
        for (int i = 0; i < this.capacity; i++){
            this.buckets.add(new LinkedList<IntEntry<V>>());
        }
    }
    @Override
    public V get(int i) {
        LinkedList<IntEntry<V>> bucket = this.buckets.get(this.getIndex(i));
        for(IntEntry<V> temp: bucket){
            if (temp.getKey() == i){
                return temp.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(int key, V value) {
        LinkedList<IntEntry<V>> bucket = this.buckets.get(this.getIndex(key));
        // TODO попробуй здесь воспользоваться итератором. Упростит код, получишь рост производительности
        ListIterator<IntEntry<V>> it = bucket.listIterator();
        it.set(new IntEntry<>(key, value));

        if (bucket.isEmpty()){
            bucket.add(new IntEntry<V>(key, value));
            this.addSize();
        }
        else {
            this.remove(key);
            bucket.add(new IntEntry<>(key, value));
            this.addSize();
        }
        return value;
    }

    @Override
    public V remove(int i) {
        LinkedList<IntEntry<V>> bucket = this.buckets.get(this.getIndex(i));
        Iterator<IntEntry<V>>  bucketIterator = bucket.iterator();
        while(bucketIterator.hasNext()){
            IntEntry<V> temp = bucketIterator.next();
            if (temp.getKey() == i){
                bucketIterator.remove();
                this.size--;
                break;
            }
        }
        return null;
    }

    @Override
    public int size() {return this.size;}

    @Override
    public boolean isEmpty() {return this.size == 0;}

    @Override
    public boolean containsKey(int i) {
        LinkedList<IntEntry<V>> bucket = this.buckets.get(this.getIndex(i));
        for (IntEntry<V> temp : bucket) {
            if (temp.getKey() == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V o) {
        for(LinkedList<IntEntry<V>> bucket: this.buckets){
            for(IntEntry<V> temp: bucket){
                if (o.compareTo(temp.getValue()) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.buckets.clear();
        this.size = 0;
    }

    @Override
    public Set<IntEntry<Person>> entrySet() {
        Set<IntEntry<Person>> entrySet = new HashSet<IntEntry<Person>>();
        for(LinkedList<IntEntry<V>> bucket: this.buckets){
            for(IntEntry<V> elem: bucket){
                entrySet.add((IntEntry<Person>)elem);
            }
        }
        return entrySet;
    }
}