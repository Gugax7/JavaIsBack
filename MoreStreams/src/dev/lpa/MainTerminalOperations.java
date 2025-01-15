package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MainTerminalOperations {

    public static void main(String[] args) {
        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent())
                .limit(1000)
                .toList();

        int minAge = 18;
        students.stream()
                .filter(student -> student.getAge() <= minAge)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d",s.getStudentId(),s.getCountryCode(),s.getAge()), () -> System.out.println("I dont find anyone that the age is lower than " + minAge));
    }

}
