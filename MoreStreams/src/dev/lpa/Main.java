package dev.lpa;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");
//        Student tim = new Student("AU", 2019, 30, "M",
//                true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);
        Student[] students = new Student[1000];
        Arrays.setAll(students, i -> Student.getRandomStudent());

//        long maleCounter = Arrays.stream(students).filter(s -> s.getGender() == "M")
//                .count();
//
//        long femaleCounter = Arrays.stream(students)
//                        .filter(s -> s.getGender() == "F")
//                                .count();
//        long undefinedCounter = Arrays.stream(students)
//                        .filter(s -> s.getGender() == "U")
//                                .count();
//
//        System.out.println(maleCounter);
//        System.out.println(femaleCounter);
//        System.out.println(undefinedCounter);

        var ageStatistics = Arrays.stream(students)
                .mapToInt(Student::getAge)
                .summaryStatistics();

        System.out.println("Max age:" + ageStatistics.getMax());
        System.out.println("Min age:" + ageStatistics.getMin());
        System.out.println("Average age:" + ageStatistics.getAverage());

        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .forEach(s -> System.out.print( s + " "));

        System.out.println();

        Arrays.stream(students)
                //.filter(s -> s.hasProgrammingExperience() && s.getAge() < 23)
                //.limit(4)
                .filter(s -> s.getPercentComplete(jmc.courseCode()) > 90)
                .peek(s -> System.out.print(s.getPercentComplete(jmc.courseCode()) + " - "))
                .forEach(System.out::println);
    }
}
