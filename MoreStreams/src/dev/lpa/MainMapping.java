package dev.lpa;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class MainMapping {
    public static void main(String[] args) {
        Course pymc= new Course("PYMC", "Python Masterclass", 50);
        Course jmc= new Course("JMC", "Java Masterclass", 100);
        Course gamesJmc = new Course("GJMC", "Creating Games In Java");

        List<Student> students = IntStream
                .rangeClosed(1,5000)
                .mapToObj(s ->Student.getRandomStudent())
                .toList();

        var mappedStudents = students.stream()
                .collect(groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k,v) -> System.out.println(k + " : " + v.size()));

        System.out.println("----------------------------");
        int minAge = 30;
        var youngStudents = students.stream()
                .collect(groupingBy(Student::getCountryCode, filtering(s->s.getAge() <= minAge, toList())));
        youngStudents.forEach((k,v) -> System.out.println(k + " : " + v.size()));


        var multiLevel = students.stream()
                .collect(groupingBy(Student::getCountryCode, groupingBy(Student::getGender, partitioningBy(Student::hasProgrammingExperience))));

        multiLevel.forEach((k,v) ->{
            System.out.println(k);
            v.forEach((k1,v1) -> {
                System.out.println("\t"+ k1);
                v1.forEach((k2,v2) ->{
                    System.out.println("\t\t " + k2 + " -> " + v2.size());

                });
                System.out.println("\tTotal = " + v1.size()); // look here that goes wrong because it only exists true and false haha
            });
        });




        long studentBodyCount = 0;
        for (var list: mappedStudents.values()){
            studentBodyCount+=list.size();
        }

        System.out.println(studentBodyCount);

        studentBodyCount = mappedStudents.values().stream()
                .mapToInt(l -> l.size())
                .sum();

        System.out.println(studentBodyCount);

        studentBodyCount = mappedStudents.values().stream()
                .map(l -> l.stream()
                        .filter(s -> s.getMonthsSinceActive() <= 1)
                        .count())
                .mapToLong(l -> l)
                .sum();

        System.out.println("Active students 1: " + studentBodyCount);

        studentBodyCount = mappedStudents.values().stream()
                .flatMap(s -> s.stream())
                .filter(s -> s.getMonthsSinceActive() <= 1)
                .count();

        System.out.println("Active students 2: " + studentBodyCount);

        studentBodyCount = multiLevel.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(map -> map.values().stream())
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 1)
                .count();

        System.out.println("Multilevel count: " + studentBodyCount);


    }
}
