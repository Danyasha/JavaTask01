package ru.sberbank.labs.lab1;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void compareToTest() {
        Person[] children = new Person[0];
        Person oldPerson = new Person(66, "test", "test", children);
        Person youngPerson = new Person(16, "test", "test", children);
        Person secondYoungPerson = new Person(16, "test", "test", children);
        ArrayList<Integer> expected = new ArrayList<>();
        ArrayList<Integer> actual = new ArrayList<>();

        actual.add(oldPerson.compareTo(youngPerson));
        expected.add(oldPerson.getAge() - youngPerson.getAge());

        actual.add(youngPerson.compareTo(oldPerson));
        expected.add(youngPerson.getAge() - oldPerson.getAge());

        actual.add(youngPerson.compareTo(secondYoungPerson));
        expected.add(youngPerson.getAge() - secondYoungPerson.getAge());

        assertEquals(expected, actual);
    }
}
