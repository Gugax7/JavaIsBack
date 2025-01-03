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

    }
}
