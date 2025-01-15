package dev.lpa;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallenge2 {

    public static void main(String[] args) {

        var students = Stream.generate(Student::getRandomStudent)
                .filter(s -> s.getYearsSinceEnrolled() >= 4)
                .limit(10000)
                .toList();


        long threeCourses = students.stream()
                .filter(s-> s.getEngagementMap().values().size() == 3)
                .count();
        long lessThan = students.stream()
                .filter(s -> s.getEngagementMap().size() < 3)
                .count();

        var javaStudents = students.stream()
                .filter(s -> s.getEngagementMap().containsKey("JMC"))
                .count();


        long notJavaStudents = students.stream()
                .map(s -> s.getEngagementMap())
                .filter(map -> !map.containsKey("JMC"))
                .count();

        System.out.println(threeCourses);
        System.out.println(lessThan);

        System.out.println(javaStudents);
        System.out.println(notJavaStudents);

        //students.forEach(System.out::println);

        var averageJavaComplete = students.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .average().getAsDouble();

        System.out.println(averageJavaComplete);

        System.out.println("____________________________");

        var multilevel = students.stream()
                .map(Student::getEngagementMap)
                .collect(Collectors.groupingBy(Map::keySet));

        multilevel.forEach((k,v) -> {
            System.out.println(k + " ---->" + v.size());
            System.out.println("_-------------_");

        });


//        students.forEach(s -> {
//            s.getEngagementMap()
//                    .forEach((k,v) ->{
//                        System.out.println(k);
//                    });
//            System.out.println("----------------------");
//        });
    }
}
