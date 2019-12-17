package ru.sberbank.labs.lab1;

import java.util.*;
/* TODO
    Все очень неплохо. Но за поставку некомпилирующегося кода в следующий раз буду сильно ругаться плохими словами.
 */
public class EntryPoint {
    // TODO попробуй сделать из этого Junit тест. Собственно это почти он и есть.

    public static Person[] personsGenerator(int size){
        String[] names = {"John", "Boris", "Jack"};
        String[] lastNames = {"Ivanov", "Testov", "Doe"};

        Random rand = new Random();
        Person[] persons = new Person[size];
        // TODO попробуй переписать этот кусок со Stream API

        for (int i = 0; i < size; i++){
            int age = rand.nextInt(99);
            String name = names[rand.nextInt(names.length)];
            String secondName = lastNames[rand.nextInt(lastNames.length)];
            persons[i] = new Person(age, name, secondName, childrenGenerator(age, secondName));
        }
        return persons;
    }

    public static Person[] childrenGenerator(int parentAge, String lastName){
        int amountOfChildren;
        int maxAge = 0;
        String[] names = {"John", "Boris", "Jack"};
        Random rand = new Random();

        // TODO Не используй if без скобок
        if (parentAge < 18) {
            amountOfChildren = 0;
        } else {
            amountOfChildren =   rand.nextInt(3);
            maxAge = parentAge - 17;
        }
        Person[] children = new Person[amountOfChildren];
        // TODO Не очень хороший паттерн - несколько точек выхода
        if (amountOfChildren != 0) {//исправил
            for (int i = 0; i < amountOfChildren; i++) {
                int age = rand.nextInt(maxAge);
                children[i] = new Person(age, names[rand.nextInt(names.length)], lastName, childrenGenerator(age, lastName));
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
        // TODO Это не скомпилируется, неверный тип
        //Интелледжи схавал и сам такую конструкцию предложил :(
        return digsSet.toArray(new Integer[0]);
    }
    public  static  float getConsumedTimeInMilliseconds(long finish, long start){
        return (finish - start) / (1000 * 1000);
    }
    public static void main(String[] args) {
        // 1. Реализуйте интерфейс Comparable в классе Person //done
        // TODO скобки
        // 2. Создайте массив Person размерностью 100_000
        // 3. Сгенерируйте 100_000 экземпляров Person с помощью генератора случайных чисел
        // Для случайной генерации строковых данных в читаемом виде можно использовать
        // предопределенные массивы строк firstname[] и lastname[]
        // 4. Поместите сгенерированные данные в массив
        int size = 100000;
        Person[] persons = personsGenerator(size);
        // 5. Создайте массив Long размерностью 100_000
        // 6. Сгенерируйте 100_000 уникальных случайных чисел в диапазоне (Long.MAX_VALUE, Long.MAX_VALUE)
        // Используйте Set<Integer> для обеспечения уникальности создаваемых чисел
        Integer[] digs = integerGenerator(size);
        // 7. Создайте экземпляры HashMap и TreMap

        Map<Integer, Person> hashMap = new HashMap<>(size);
        Map<Integer, Person> treeMap = new TreeMap<>();
        // 8. Используя созданный массив Integer[] и Person[] поместите
        // данные в созданные структуры, измерьте время, затраченное на запись данных для TreeMap и HashMap
        long start = System.nanoTime();
        // TODO скобки
        for (int i = 0; i < size; i++) {
            hashMap.put(digs[i], persons[i]);
        }
        long finish = System.nanoTime();
        System.out.printf("add hashMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        start = System.nanoTime();
        // TODO скобки
        for (int i = 0; i < size; i++) {
            treeMap.put(digs[i], persons[i]);
        }
        finish = System.nanoTime();
        System.out.printf("add treeMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        // 9. Используя массив Integer[] извлеките данные из структур данных, измерьте время, затраченное на чтение
        start = System.nanoTime();
        // TODO Это не скомпилируется, неверный тип
        Integer hashMapKeys[] = hashMap.keySet().toArray(new Integer[0]);
        finish = System.nanoTime();

        System.out.printf("read hashMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        start = System.nanoTime();
        // TODO Это не скомпилируется, неверный тип
        Integer treeMapKeys[] = treeMap.keySet().toArray(new Integer[0]);
        finish = System.nanoTime();
        System.out.printf("read treeMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        // 10. Улучшите показатели HashMap на запись, изучив доступные конструкторы HashMap
        //Map<Integer, Person> hashMapWithCap = new HashMap<>(size);
        // 11. Создайте свою собственную структуру данных, реализовав интерфейс IntMap
        // Изучите реализацию соответствующих методов в стандартной реализации HashMap, используйте их
        // как руководство к действию.
        // 12. Измерьте производительность созданной структуры данных.

        // P.S. Опционально: реализовать все измерительные процедуры в junit тестах
    }
}
