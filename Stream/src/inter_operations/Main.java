package inter_operations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        IntStream.iterate((int)'A', i -> i < (int)'z', i->i+1)
                .skip(5)
                .filter(Character::isAlphabetic)
                .takeWhile(i -> i < (int)'g')
                .forEach(i -> System.out.printf("%c ", i));

        List<Character> list = new ArrayList<>();
        for(int i = 'a'; i <= 'z'; i++){
            list.add((char)i);
        }
        System.out.println("=======================");

        list.stream()
                .skip(5)
                .limit(10)
                .forEach(s -> System.out.print(s+ " "));
    }


}
