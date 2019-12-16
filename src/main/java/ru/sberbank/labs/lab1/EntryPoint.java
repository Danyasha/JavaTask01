package ru.sberbank.labs.lab1;

import java.awt.*;
import java.util.*;

public class EntryPoint {
    public static boolean personTest() {
        boolean isOk = true;
        Person[] children = new Person[0];
        Person oldPerson = new Person(66, "test", "test", children);
        Person youngPerson = new Person(16, "test", "test", children);
        Person secondYoungPerson = new Person(16, "test", "test", children);
        if (oldPerson.compareTo((youngPerson)) < 0){
            isOk = false;
        }
        if (youngPerson.compareTo((oldPerson)) > 0){
            isOk = false;
        }
        if (youngPerson.compareTo((secondYoungPerson)) != 0){
            isOk = false;
        }
        if (isOk)
            return true;
        return false;
    }

    public static Person[] personsGenerator(int size){
        String[] names = {"John", "Boris", "Jack"};
        String[] lastNames = {"Ivanov", "Testov", "Doe"};

        Random rand = new Random();
        Person[] persons = new Person[size];
        for (int i = 0; i < size; i++){
            int age = rand.nextInt(99);
            String name = names[rand.nextInt(names.length)];
            String secondName = lastNames[rand.nextInt(lastNames.length)];
            persons[i] = new Person(age, name, secondName, childrenGenetator(age, secondName));
        }
        return persons;
    }

    public static Person[] childrenGenetator(int parentAge, String lastName){
        int amountOfChildren;
        int maxAge = 0;
        String[] names = {"John", "Boris", "Jack"};
        Random rand = new Random();

        if (parentAge < 18)
            amountOfChildren = 0;
        else{
            amountOfChildren =   rand.nextInt(3);
            maxAge = parentAge - 17;
        }
        Person[] children = new Person[amountOfChildren];
        if (amountOfChildren == 0)
            return children;
        for (int i = 0; i < amountOfChildren; i++){
            int age = rand.nextInt(maxAge);
            children[i] = new Person(age, names[rand.nextInt(names.length)], lastName, childrenGenetator(age, lastName));
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
        return digsSet.toArray(Integer[]::new);
    }
    public  static  float getConsumedTimeInMilliseconds(long finish, long start){
        return (finish - start) / (1000 * 1000);
    }
    public static void main(String[] args) {
        // 1. Реализуйте интерфейс Comparable в классе Person //done
        if (!personTest())
            System.out.println("Person compare doesnt working");
        else
            System.out.println("Person compare ok");
        // 2. Создайте массив Person размерностью 100_000
        // 3. Сгенерируйте 100_000 экземпляров Person с помощью генератора случайных чисел
        // Для случайной генерации строковых данных в читаемом виде можно использовать
        // предопределенные массивы строк firstname[] и lastname[]
        // 4. Поместите сгенерированные данные в массив
        int size = 1000000;
        Person[] persons = personsGenerator(size);
        // 5. Создайте массив Long размерностью 100_000
        // 6. Сгенерируйте 100_000 уникальных случайных чисел в диапазоне (Long.MAX_VALUE, Long.MAX_VALUE)
        // Используйте Set<Integer> для обеспечения уникальности создаваемых чисел
        Integer[] digs = integerGenerator(size);
        // 7. Создайте экземпляры HashMap и TreMap

        Map<Integer, Person> hashMap = new HashMap<>();
        Map<Integer, Person> treeMap = new TreeMap<>();
        // 8. Используя созданный массив Integer[] и Person[] поместите
        // данные в созданные структуры, измерьте время, затраченное на запись данных для TreeMap и HashMap
        long start = System.nanoTime();
        for (int i = 0; i < size; i++)
            hashMap.put(digs[i], persons[i]);
        long finish = System.nanoTime();
        System.out.printf("time for add hashMap in milliseconds:  %f\n", getConsumedTimeInMilliseconds(finish, start));
        start = System.nanoTime();
        treeMap.putAll(hashMap);
        finish = System.nanoTime();
        System.out.printf("time for add treeMap in milliseconds:  %f\n", getConsumedTimeInMilliseconds(finish, start));
        // 9. Используя массив Integer[] извлеките данные из структур данных, измерьте время, затраченное на чтение

        // 10. Улучшите показатели HashMap на запись, изучив доступные конструкторы HashMap
        Map<Integer, Person> hashMapWithCap = new HashMap<>(size);
        start = System.nanoTime();
        for (int i = 0; i < size; i++)
            hashMapWithCap.put(digs[i], persons[i]);
        finish = System.nanoTime();
        System.out.printf("time for add hashMapWithCap add in milliseconds:  %f\n", getConsumedTimeInMilliseconds(finish, start));
        // 11. Создайте свою собственную структуру данных, реализовав интерфейс IntMap
        // Изучите реализацию соответствующих методов в стандартной реализации HashMap, используйте их
        // как руководство к действию.
        // 12. Измерьте производительность созданной структуры данных.

        // P.S. Опционально: реализовать все измерительные процедуры в junit тестах
    }
}

