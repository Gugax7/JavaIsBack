import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    }
}