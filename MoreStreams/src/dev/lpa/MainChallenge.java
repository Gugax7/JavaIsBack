package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallenge {

    public static void main(String[] args) {
        Course pymc= new Course("PYMC", "Python Masterclass", 50);
        Course jmc= new Course("JMC", "Java Masterclass", 100);
        Course gamesJmc = new Course("GJMC", "Creating Games In Java");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent())
                .limit(5000)
                .toList();

        Double sum = students.stream()
                //.map(Student::getPercentComplete)
                .map(s -> s.getPercentComplete("JMC"))
                .reduce(0.0, Double::sum);

        Double average = sum / 5000;

        System.out.println(average);

        var summary = students.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .summaryStatistics();

        System.out.println(summary.getAverage());

        Set<Student> mySet = students.stream()
                .filter(s -> s.getPercentComplete("JMC") > average * 1.25)
                .filter(s -> s.getMonthsSinceActive("JMC") < 1)
                //.collect(() -> new TreeSet<>(Comparator.comparing(Student::getYearsSinceEnrolled)), TreeSet::add, TreeSet::addAll);
                .collect(Collectors.toSet());
        System.out.println("Number of students that already finished more than three quarters of jmc and still active: " + mySet.size());

        //mySet.forEach(System.out::println);

        List<Student> hardWorkers = mySet.stream()
                .sorted(Comparator.comparing(Student::getYearEnrolled))
                .toList();

        hardWorkers.forEach(System.out::println);


    }
}
