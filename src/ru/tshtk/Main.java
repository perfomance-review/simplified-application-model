package ru.tshtk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //Add person name here
        String[] names = {"Oleg", "Igor", "Pasha", "Sveta", "Olya"};

        List<Person> users = getUsers(names);

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

    private static List<Person> getUsers(String[] names) {
        return Arrays.stream(names).map(Person::new).toList();
    }


    private static ArrayList<Pair> getPairs(List<Person> users) {
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < users.size() ; i++) {
            for (int j = i+1; j < users.size() ; j++) {
                Pair pair = new Pair(users.get(i), users.get(j));
                pairs.add(pair);
            }
        }
        return pairs;
    }

    private static ArrayList<Grade> getGrades(List<Person> users, ArrayList<Pair> pairs) {
        ArrayList<Grade> grades = new ArrayList<>();
        Person chosen;
        Person ignored;
        Random random = new Random();
        for (Person user: users) {
            for (Pair pair: pairs) {
                if (user.equals(pair.getPerson1()) || user.equals(pair.getPerson2())) {
                    continue;
                }
                chosen = pair.getPerson1();
                ignored = pair.getPerson2();
                int i = random.nextInt(2);
                if (i == 0) {
                    chosen = pair.getPerson2();
                    ignored = pair.getPerson1();
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
