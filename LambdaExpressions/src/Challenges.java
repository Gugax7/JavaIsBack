import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Challenges {

    public static void main(String[] args) {

        // challenge 1
        Consumer<String> printTheParts = s -> {
            String[] parts = s.split(" ");
            Arrays.asList(parts).forEach(string -> System.out.println(string));
        };
        printTheParts.accept("part1 part2 part3 part4 part5");
        // challenge 2 - 5
        System.out.println(everySecondChar("letmesee"));
        Function<String,String> everySecondChar = (String s) -> {
            StringBuilder returnVal = new StringBuilder();
            for(int i = 0; i < s.length(); i++){
                if(i%2 == 1){
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };

        //UnaryOperator cam be used instead of function, but for me its more neat using function

        System.out.println(everySecondChar.apply("1234567890"));

        everySecondCharacter("1234567890",everySecondChar);
        // challenge 6
        Supplier<String> iLovaJava = () -> "I love java";

        // challenge 7
        String supplierResult = iLovaJava.get();
        System.out.println(supplierResult);

    }
    // challenge 4
    public static String everySecondChar(String source){
        StringBuilder returnVal = new StringBuilder();
        for(int i = 0; i < source.length(); i++){
            if(i % 2 == 1){
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }
    // challenge 5
    public static void everySecondCharacter(String source, Function<String,String> function){
        System.out.println(function.apply(source));
    }


}
