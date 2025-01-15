package dev.lpa;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {

    public static void main(String[] args) {


        List<Student> students = Stream.generate(Student::getRandomStudent)
                .limit(1000)
                .collect(Collectors.toList());

        students.add(0,null);

        Optional<Student> o1 = getStudent(students,"firt");
        System.out.println(o1);

        o1 = Optional.empty();

        Student mySon = o1.orElse(new Student("BR",0,0,"M",false));

        System.out.println(mySon);

        o1.ifPresentOrElse(System.out::println, ()-> System.out.println("--->Is empty"));

    }

    private static Optional<Student> getStudent(List<Student> list, String type){
        if(list == null || list.isEmpty()){
            return Optional.empty();
        } else if (type.equals("first")) {
            return Optional.ofNullable(list.get(0));
        } else if (type.equals("last")) {
            return Optional.ofNullable(list.get(list.size() - 1));
        }
        return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
    }


}
