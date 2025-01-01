import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class Challenges {

    public static void main(String[] args) {
        Consumer<String> printTheParts = s -> {
            String[] parts = s.split(" ");
            Arrays.asList(parts).forEach(string -> System.out.println(string));
        };
        printTheParts.accept("part1 part2 part3 part4 part5");

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

        System.out.println(everySecondChar.apply("letmesee"));
    }

    public static String everySecondChar(String source){
        StringBuilder returnVal = new StringBuilder();
        for(int i = 0; i < source.length(); i++){
            if(i % 2 == 1){
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

}
