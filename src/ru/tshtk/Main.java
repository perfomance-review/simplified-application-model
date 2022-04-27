package ru.tshtk;

import java.util.*;

public class Main {
    private final static Random random = new Random();
    private final static int RESPONDENTS_COUNT = 10; // can be set from 2 to 10
    private final static String[] NAMES = {"Oleg", "Igor", "Pasha", "Svetlana", "Olga", "Petya", "Yaroslav", "Dasha", "Sergey", "Armen"};


    public static void main(String[] args) {

        List<Person> users = getUsers(NAMES, RESPONDENTS_COUNT);

        System.out.println("Users count: " + users.size());
        System.out.println("Users: " + users + "\n");

        ArrayList<Pair> pairs = getPairs(users);
        System.out.println("Pairs count: " + pairs.size());
        System.out.println("Pairs: " + pairs + "\n");

        ArrayList<Grade> grades = getGrades(users, pairs);

        System.out.println("Grades count: " + grades.size());
        System.out.println("Grades:" + grades + "\n");

        ArrayList<Result> results = getResults(users, grades);

        System.out.println("Results size: " + results.size());
        System.out.println("Results: " + results);
    }

    private static List<Person> getUsers(String[] names, int count) {
        return Arrays.stream(names).limit(count).map(Person::new).toList();
    }

    private static ArrayList<Pair> getPairs(List<Person> users) {
        ArrayList<Pair> pairs = new ArrayList<>();
        Pair pair;
        for (int i = 0; i < users.size() ; i++) {
            for (int j = i+1; j < users.size() ; j++) {

                if (random.nextInt(2) == 0) {
                    pair = new Pair(users.get(i), users.get(j));
                }
                else {
                    pair = new Pair(users.get(j), users.get(i));
                }
                pairs.add(pair);
            }
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private static ArrayList<Grade> getGrades(List<Person> users, ArrayList<Pair> pairs) {
        ArrayList<Grade> grades = new ArrayList<>();
        Person chosen;
        Person ignored;
        for (Person user: users) {
            for (Pair pair: pairs) {
                if (user.equals(pair.getPerson1()) || user.equals(pair.getPerson2())) {
                    continue;
                }
                if (random.nextInt(2) == 0) {
                    chosen = pair.getPerson2();
                    ignored = pair.getPerson1();
                }
                else {
                    chosen = pair.getPerson1();
                    ignored = pair.getPerson2();
                }
                Grade grade = new Grade(user, chosen, ignored);
                grades.add(grade);
            }
        }
        return grades;
    }

    private static ArrayList<Result> getResults(List<Person> users, ArrayList<Grade> grades) {
        ArrayList<Result> results = new ArrayList<>();

        for (Person user: users) {
            long chosenCount = grades.stream()
                    .filter(o -> user.equals(o.getChosen()))
                    .count();
            long ignoredCount = grades.stream()
                    .filter(o -> user.equals(o.getIgnored()))
                    .count();
            double score = 10.0 * chosenCount/(chosenCount + ignoredCount);
            Result result = new Result(user, score);
            results.add(result);
        }
        return results;
    }
}
