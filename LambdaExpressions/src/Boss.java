import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Boss {
    public static void main(String[] args) {
        String[] names = {"Bob", "Anna", "Gustavo", "Natan", "Yago"};
        Arrays.setAll(names,(i)->names[i].toLowerCase());
        System.out.println(Arrays.toString(names));
        Arrays.setAll(names,(i)->names[i].toUpperCase());
        System.out.println(Arrays.toString(names));

        List<String> myList = Arrays.asList(names);

        System.out.println("-".repeat(30));
        myList.replaceAll(s -> s+= " " + getRandomChar('A', 'V') + ".");
        myList.replaceAll(s -> s+= " " + getReversedName(s.split(" ")[0]));
        myList.forEach(System.out::println);
        //System.out.println(Arrays.toString(names));
        //i notice here that i was changing the array at same time too. interesting.
        System.out.println("-".repeat(30));
        List<String> myNewList = new ArrayList<>(List.of(names));
        // my way to compare:
        //myNewList.removeIf(s -> s.split(" ")[0].compareTo(s.split(" ")[2]) == 0);
        // teacher way:
        myNewList.removeIf(s -> s.substring(0,s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") + 1)));
        myNewList.forEach(s -> System.out.println(s));
    }
    public static char getRandomChar(char startChar, char endChar){
        return (char) new Random().nextInt((int) startChar,(int) endChar);
    }
    public static String getReversedName(String firstName){
        return new StringBuilder(firstName).reverse().toString();
    }
}
