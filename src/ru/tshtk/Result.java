package ru.tshtk;

public class Result {
    private final Person person;
    private final double score;

    public Result(Person person, double score) {
        this.person = person;
        this.score = score;
    }

    @Override
    public String toString() {
        return "{" + person + ", " + (Math.round(score * 100) / 100.0) + '}';
    }
}
