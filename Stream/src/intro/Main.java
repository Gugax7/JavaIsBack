package intro;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        int first = 1;
        for(char c : "BINGO".toCharArray()){
            for(int i = first; i < first + 15; i++){
                list.add(c + "-" + i);
            }
            first+=15;
        }

        Collections.shuffle(list);

        list.stream()
                        .limit(15)
                                .filter(s ->s.indexOf('G') == 0 || s.indexOf('O') == 0)
                                        .sorted()
                                                .forEach(System.out::println);

        System.out.println(list);

        String[] array = {"One", "Orange", "Building", "Intense"};
        Arrays.stream(array)
                .sorted()
                .map(s -> s+"!")
                .forEach(System.out::println);

        Map<Character,int[]> bingo = new LinkedHashMap<>();
        int bingoIndex = 1;
        for(char c : "BINGO".toCharArray()){
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers,i -> labelNo + i);
            bingo.put(c,numbers);
            bingoIndex+=15;
        }

        bingo.entrySet().stream()
                .map(e -> e.getKey() + " : " + e.getValue()[0] + " to " + e.getValue()[e.getValue().length - 1])
                .forEach(System.out::println);


        var b = Stream.of(fillLetterStartingWith('B',1));
        var i = Stream.of(fillLetterStartingWith('I',16));
        var n = Stream.of(fillLetterStartingWith('N',31));
        var g = Stream.of(fillLetterStartingWith('G',46));
        var o = Stream.of(fillLetterStartingWith('O',61));

        var bi = Stream.concat(b,i);
        var ng = Stream.concat(n,g);
        var bing = Stream.concat(bi,ng);
        Stream.concat(bing,o)
                .forEach(s -> System.out.print(s + " "));
    }

    public static String[] fillLetterStartingWith(char letter, int number){
        String[] array = new String[15];
        Arrays.setAll(array,i -> "" + letter + "" + (i+number));
        return array;
    }
}