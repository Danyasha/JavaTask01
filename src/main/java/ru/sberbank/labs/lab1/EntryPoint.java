package ru.sberbank.labs.lab1;

import java.util.*;
/* TODO
    ��� ����� �������. �� �� �������� ������������������ ���� � ��������� ��� ���� ������ �������� ������� �������.
 */
public class EntryPoint {
    // TODO �������� ������� �� ����� Junit ����. ���������� ��� ����� �� � ����.
    public static boolean personTest() {
        boolean isOk = true;
        Person[] children = new Person[0];
        Person oldPerson = new Person(66, "test", "test", children);
        Person youngPerson = new Person(16, "test", "test", children);
        Person secondYoungPerson = new Person(16, "test", "test", children);
        // TODO - made in India
        if (oldPerson.compareTo((youngPerson)) < 0){
            isOk = false;
        }
        if (youngPerson.compareTo((oldPerson)) > 0){
            isOk = false;
        }
        if (youngPerson.compareTo((secondYoungPerson)) != 0){
            isOk = false;
        }
        // TODO - made in India => return isOk
        if (isOk)
            return true;
        return false;
    }

    public static Person[] personsGenerator(int size){
        String[] names = {"John", "Boris", "Jack"};
        String[] lastNames = {"Ivanov", "Testov", "Doe"};

        Random rand = new Random();
        Person[] persons = new Person[size];
        // TODO �������� ���������� ���� ����� �� Stream API
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

        // TODO �� ��������� if ��� ������
        if (parentAge < 18) {
            amountOfChildren = 0;
        } else {
            amountOfChildren =   rand.nextInt(3);
            maxAge = parentAge - 17;
        }
        Person[] children = new Person[amountOfChildren];
        // TODO �� ����� ������� ������� - ��������� ����� ������
        if (amountOfChildren == 0) {
            return children;
        }
        for (int i = 0; i < amountOfChildren; i++){
            int age = rand.nextInt(maxAge);
            children[i] = new Person(age, names[rand.nextInt(names.length)], lastName, childrenGenerator(age, lastName));
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
        // TODO ��� �� ��������������, �������� ���
        //return digsSet.toArray(Integer[]::new);
        return digsSet.toArray(new Integer[0]);
    }
    public  static  float getConsumedTimeInMilliseconds(long finish, long start){
        return (finish - start) / (1000 * 1000);
    }
    public static void main(String[] args) {
        // 1. ���������� ��������� Comparable � ������ Person //done
        // TODO ������
        if (!personTest())
            System.out.println("Person compare doesnt working");
        else
            System.out.println("Person compare ok");
        // 2. �������� ������ Person ������������ 100_000
        // 3. ������������ 100_000 ����������� Person � ������� ���������� ��������� �����
        // ��� ��������� ��������� ��������� ������ � �������� ���� ����� ������������
        // ���������������� ������� ����� firstname[] � lastname[]
        // 4. ��������� ��������������� ������ � ������
        int size = 100000;
        Person[] persons = personsGenerator(size);
        // 5. �������� ������ Long ������������ 100_000
        // 6. ������������ 100_000 ���������� ��������� ����� � ��������� (Long.MAX_VALUE, Long.MAX_VALUE)
        // ����������� Set<Integer> ��� ����������� ������������ ����������� �����
        Integer[] digs = integerGenerator(size);
        // 7. �������� ���������� HashMap � TreMap

        Map<Integer, Person> hashMap = new HashMap<>(size);
        Map<Integer, Person> treeMap = new TreeMap<>();
        // 8. ��������� ��������� ������ Integer[] � Person[] ���������
        // ������ � ��������� ���������, �������� �����, ����������� �� ������ ������ ��� TreeMap � HashMap
        long start = System.nanoTime();
        // TODO ������
        for (int i = 0; i < size; i++)
            hashMap.put(digs[i], persons[i]);
        long finish = System.nanoTime();
        System.out.printf("add hashMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        start = System.nanoTime();
        // TODO ������
        for (int i = 0; i < size; i++)
            treeMap.put(digs[i], persons[i]);
        finish = System.nanoTime();
        System.out.printf("add treeMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        // 9. ��������� ������ Integer[] ��������� ������ �� �������� ������, �������� �����, ����������� �� ������
        start = System.nanoTime();
        // TODO ��� �� ��������������, �������� ���
        //Integer hashMapKeys[] = hashMap.keySet().toArray(Integer[]::new);
        Integer hashMapKeys[] = hashMap.keySet().toArray(new Integer[0]);
        finish = System.nanoTime();

        System.out.printf("read hashMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        start = System.nanoTime();
        // TODO ��� �� ��������������, �������� ���
        Integer treeMapKeys[] = treeMap.keySet().toArray(new Integer[0]);
        finish = System.nanoTime();
        System.out.printf("read treeMap in ms:\t%f\n", getConsumedTimeInMilliseconds(finish, start));
        // 10. �������� ���������� HashMap �� ������, ������ ��������� ������������ HashMap
        //Map<Integer, Person> hashMapWithCap = new HashMap<>(size);
        // 11. �������� ���� ����������� ��������� ������, ���������� ��������� IntMap
        // ������� ���������� ��������������� ������� � ����������� ���������� HashMap, ����������� ��
        // ��� ����������� � ��������.
        // 12. �������� ������������������ ��������� ��������� ������.

        // P.S. �����������: ����������� ��� ������������� ��������� � junit ������
    }
}
