import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] items = {"gustavo", "guilherme", "giovana", "goes", "girondino"};
        List<String> list = List.of(items);
        System.out.println(list);

        //list.add("strawberry"); it doesn't work

        ArrayList<String> groceries = new ArrayList<>(list);
        groceries.add("garmagedon");
        groceries.remove(2);
        System.out.println(groceries);

        ArrayList<String> newList = new ArrayList<>(List.of("gota", "gabriel", "gabriela"));
        groceries.addAll(newList);
        System.out.println(groceries);

        groceries.sort(Comparator.naturalOrder());

        System.out.println(groceries);

        var array = groceries.toArray(new String[groceries.size()]);

        System.out.println(Arrays.toString(array));
        
    }
}