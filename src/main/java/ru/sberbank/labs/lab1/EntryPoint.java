package ru.sberbank.labs.lab1;

import java.util.*;

public class EntryPoint {

    public static Person[] personsGenerator(int size){
        String[] names = {"John", "Boris", "Jack"};
        String[] lastNames = {"Ivanov", "Testov", "Doe"};

        Random rand = new Random();
        Person[] persons = new Person[size];
        for (int i = 0; i < size; i++){
            int age = rand.nextInt(99);
            String name = names[rand.nextInt(names.length)];
            String secondName = lastNames[rand.nextInt(lastNames.length)];
            persons[i] = new Person(age, name, secondName, childrenGenerator(age, secondName));
        }
        return persons;
    }

    public static Person[] childrenGenerator(int parentAge, String secondName){
        int amountOfChildren;
        int maxAge = 0;
        String[] names = {"John", "Boris", "Jack"};
        Random rand = new Random();

        if (parentAge < 18) {
            amountOfChildren = 0;
        } else {
            amountOfChildren =  rand.nextInt(3);
            maxAge = parentAge - 17;
        }
        Person[] children = new Person[amountOfChildren];
        if (amountOfChildren != 0) {//исправил
            for (int i = 0; i < amountOfChildren; i++) {
                int age = rand.nextInt(maxAge);
                children[i] = new Person(age, names[rand.nextInt(names.length)], secondName,
                        childrenGenerator(age, secondName));
            }
        }
        return children;
    }

    public static Integer[] integerGenerator(int size) {
        Random rand = new Random();
        HashSet<Integer> digsSet = new HashSet<>(size);
        int temp = 0;
        for (int i = 0; i < size; i++) {
            while(digsSet.contains(temp)){
                temp = rand.nextInt(Integer.MAX_VALUE) - rand.nextInt(Integer.MAX_VALUE) - 1;
            }
            digsSet.add(temp);
        }
        return digsSet.toArray(new Integer[0]);
    }
    public  static  float getConsumedTimeInMilliseconds(long finish, long start){
        return (finish - start) / (1000 * 1000);
    }
    public static void main(String[] args) {
        // 1. Реализуйте интерфейс Comparable в классе Person //done
        // 2. Создайте массив Person размерностью 100_000
        // 3. Сгенерируйте 100_000 экземпляров Person с помощью генератора случайных чисел
        // Для случайной генерации строковых данных в читаемом виде можно использовать
        // предопределенные массивы строк firstname[] и lastname[]
        // 4. Поместите сгенерированные данные в массив
        int size = 100000;
        Person[] persons = personsGenerator(size);
        Integer[] digs = integerGenerator(size);
        // 7. Создайте экземпляры HashMap и TreMap

        Map<Integer, Person> hashMap = new HashMap<>(size);
        Map<Integer, Person> treeMap = new TreeMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            hashMap.put(digs[i], persons[i]);
        }
        long finish = System.nanoTime();
        System.out.printf("add hashMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            treeMap.put(digs[i], persons[i]);
        }
        finish = System.nanoTime();
        System.out.printf("add treeMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));

        for (int i = 0; i < size; i++) {
            hashMap.get(digs[i]);
        }
        finish = System.nanoTime();

        System.out.printf("read hashMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        // TODO то же что и выше
        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            treeMap.get(digs[i]);
        }
        finish = System.nanoTime();
        System.out.printf("read treeMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        MyHashMap<Person> myHashMap = new MyHashMap<>(size, 0.75);
        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            myHashMap.put(digs[i], persons[i]);
        }
        finish = System.nanoTime();
        System.out.printf("write time myHashMap ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        start = System.nanoTime();
        Set<IntEntry<Person>> entrySet = myHashMap.entrySet();
        finish = System.nanoTime();
        System.out.printf("read time myHashMap ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        int n = 50;
        System.out.println(Integer.hashCode(n));
    }
}
