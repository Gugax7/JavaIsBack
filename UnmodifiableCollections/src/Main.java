import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder bobNotes = new StringBuilder();
        StringBuilder billNotes = new StringBuilder("Bill has problems with generics");

        Student bob = new Student("Bob", bobNotes);
        Student bill = new Student("Bill", billNotes);

        List<Student> students = new ArrayList<>(List.of(bob,bill));
        List<Student> studentsFirstCopy = new ArrayList<>(students);

        bobNotes.append("Bob was one of my students");

        students.forEach(System.out::println);
        System.out.println("-".repeat(30));
        studentsFirstCopy.forEach(System.out::println);
        System.out.println("-".repeat(30));


    }
}