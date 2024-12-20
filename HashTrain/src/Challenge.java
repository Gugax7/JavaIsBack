import java.util.HashSet;

public class Challenge {

    public static void main(String[] args) {
        HashSet<String> uniqueNames = new HashSet<>();


        uniqueNames.add("Gustavo");
        uniqueNames.add("Gustavo");
        uniqueNames.add("Guilherme");
        uniqueNames.add("Giovana");
        uniqueNames.add("Diego");
        uniqueNames.add("Kaue");
        uniqueNames.add("Gustavo");



        System.out.println(uniqueNames);
    }
}
