import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

public class Main {
    record People(String firstName, String lastName){
        public String toString(){
            return firstName + " " + lastName;
        }
    }
    public static void main(String[] args) {
        List<People> people = new ArrayList<>(Arrays.asList(
                new People("Gustavo", "Salmazo"),
                new Main.People("Ana", "Maria"),
                new People("Guimaraes", "Rosa"),
                new People("Joao", "Guilherme")

        ));

        people.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        System.out.println(people);

        interface EnhancedComparator<T> extends Comparator<People>{
            int secondLevel(T o1, T o2);
        }

        var comparatorMixed = new EnhancedComparator<People>(){
            @Override
            public int compare(People o1, People o2){
                int result = o1.lastName().compareTo(o2.lastName());
                return (result == 0 ? secondLevel(o1,o2) : result);
            }
            public int secondLevel(People o1, People o2){
                return o1.firstName().compareTo(o2.lastName());
            }
        };
        people.sort(comparatorMixed);
        people.removeIf(people1 -> people1.firstName.startsWith("Gu"));
        System.out.println(people);

        //var result = calculator((Integer a,Integer b) -> System.out.println(a + b), 3,2);
        calculator((Integer a,Integer b) -> System.out.println(a + b), 3,2);
    }
    public static <T> void calculator(BiConsumer<T, T> function, T value, T value2){
        //T result = function.accept(value,value2);
        function.accept(value,value2);
    }

}