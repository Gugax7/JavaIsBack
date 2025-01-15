package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {

    public static void main(String[] args) {
        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent())
                .limit(1000)
                .toList();


        Set<Student> australianStudents = students.stream()
                .filter(s -> s.getCountryCode().equals("AU"))
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add,TreeSet::addAll);
        Set<Student> underThirty = students.stream()
                .filter(s -> s.getAge() < 30)
                .collect(Collectors.toSet());
        Set<Student> newtSet = new TreeSet<>(Comparator.comparing(Student::getStudentId));
        newtSet.addAll(australianStudents);
        newtSet.retainAll(underThirty);
        System.out.println();
        newtSet.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println();
        australianStudents.stream().filter(s -> s.getAge() < 30)
                .forEach(s -> System.out.print(s.getStudentId() + " "));

        String studentCountries = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .reduce("", (r,v)-> r + " " + v);

        System.out.println("Countries: " + studentCountries);
    }

}
