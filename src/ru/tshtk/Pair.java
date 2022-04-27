package ru.tshtk;

public class Pair {
    private final Person Person1;
    private final Person Person2;

    public Pair(Person person1, Person person2) {
        Person1 = person1;
        Person2 = person2;
    }

    @Override
    public String toString() {
        return "{" + Person1 + ", " + Person2 + '}';
    }

    public Person getPerson1() {
        return Person1;
    }

    public Person getPerson2() {
        return Person2;
    }
}
