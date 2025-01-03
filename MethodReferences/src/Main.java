import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {

        String[] names = {"Gustavo", "Giovana", "Guilherme", "Natan","Yago","Paula"};
        List<UnaryOperator<String>> operators = new ArrayList<>();
        operators.add(String::toUpperCase);
        operators.add(name -> name+=" " + pickRandomChar('A', 'Z') + ".");
        operators.add(name -> name+=" " + reverseName(name.split(" ")[0]));
        operators.add(String::toLowerCase);
        operators.add(String::new);
        operators.add(s -> new String("Hell yeah " + s));

        List<String> namesList = Arrays.asList(names);

        for(int i = 0; i < operators.size(); i++){
            namesList.replaceAll(operators.get(i));
        }


        namesList.forEach(System.out::println);
    }
    public static char pickRandomChar(char initialChar, char endChar){
        return (char)new Random().nextInt((int)initialChar, (int)endChar);
    }
    public static String reverseName(String name){
        return new StringBuilder(name).reverse().toString();
    }
}







//        String name = "AAaAaAAa";
//        Integer number = 12;
//        Function<Integer,Integer> op = Integer::reverse;
//        System.out.println(op.apply(number));
//
//        String result = name.transform(String::toLowerCase);
//        System.out.println(result);
