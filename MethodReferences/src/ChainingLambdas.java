import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainingLambdas {

    public static void main(String[] args) {
        String name = "Gustavo";
        Function<String,String> f1 = String::toUpperCase;
        Function<String, String> f2 = s -> s+= " Salmazo";
        Function<String,String> f1and2 = f1.andThen(f2);
        Function<String,String>f2and1 = f1.compose(f2);
        Function<String, String> f3 = f1
                .andThen(s -> s+= s.charAt(0))
                        .andThen(s-> s.concat("  "))
                                .andThen(s -> "Hell yeah " + s)
                                        .andThen(f2);

        System.out.println(f3.apply(name));

        record Person(String firstName, String lastName){}

        List<Person> people = new ArrayList<>(List.of(
                new Person("Gustavo", "Salmazo"),
                new Person("Peter", "Peterson"),
                new Person("Mary", "Jane"),
                new Person("Jota", "Johnson"),
                new Person("Peter", "Parker")
        ));

//        people.sort((o1,o2) -> {
//            if(o1.firstName().equals(o2.firstName())){
//                return o1.lastName().compareTo(o2.lastName());
//            }
//            return o1.firstName().compareTo(o2.firstName);
//        });
        people.sort(Comparator.comparing(Person::firstName)
                .thenComparing(Person::lastName));
        people.forEach(System.out::println);

    }
}
