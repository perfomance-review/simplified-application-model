package ru.tshtk;

public class Grade {
    private final Person voter;
    private final Person chosen;
    private final Person ignored;

    public Grade(Person voter, Person chosen, Person ignored) {
        this.voter = voter;
        this.chosen = chosen;
        this.ignored = ignored;
    }

    @Override
    public String toString() {
        return "{" +
                "voter=" + voter +
                ", chosen=" + chosen +
                ", ignored=" + ignored +
                '}';
    }

    public Person getVoter() {
        return voter;
    }

    public Person getChosen() {
        return chosen;
    }

    public Person getIgnored() {
        return ignored;
    }
}
