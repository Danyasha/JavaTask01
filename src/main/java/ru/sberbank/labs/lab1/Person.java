package ru.sberbank.labs.lab1;

import java.util.Arrays;

public final class Person implements Comparable<Person> {
    private final int age;
    private final String firstName;
    private final String lastName;
    private final Person[] children;

    public Person(int age, String firstName, String lastName, Person[] children) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.children = Arrays.copyOf(children, children.length, Person[].class);
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person[] getChildren() {
        return Arrays.copyOf(children, children.length, Person[].class);
    }

    @Override
    public int compareTo(Person other) {
        int result = this.getLastName().compareTo(other.getLastName());
        if (result != 0) {
            return result;
        } else {
            return this.getAge() - other.getAge();
        }
    }
}
