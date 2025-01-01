import javax.swing.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
        people.replaceAll(o -> new People("Sergio", "Alcantara"));
        System.out.println(people);
        //var result = calculator((Integer a,Integer b) -> System.out.println(a + b), 3,2);


        calculator((Integer a,Integer b) -> System.out.println(a + b), 3,2);

        String[] stringArray = new String[10];
        System.out.println(Arrays.toString(stringArray));

        Arrays.fill(stringArray,"");
        Arrays.setAll(stringArray,(i)->{
            return switch (i){
                case 0 -> "zero";
                case 1 -> "one";
                case 7 -> "seven";
                default -> ""+i;
            };

        });

        System.out.println(Arrays.toString(stringArray));

        String[] names = {"Carol", "Gustavo", "Leo", "Mika", "Thiago"};
        String[] randomList = randomlySelectedValues(20,names,()-> new Random().nextInt(0,names.length));

        System.out.println(Arrays.toString(randomList));


    }

    public static <T> void calculator(BiConsumer<T, T> function, T value, T value2){
        //T result = function.accept(value,value2);
        function.accept(value,value2);
    }

    public static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> supplier){
        String[] selectedValues = new String[count];
        for(int i = 0; i < count; i++){
            selectedValues[i] = values[supplier.get()];
        }
        return selectedValues;
    }

}