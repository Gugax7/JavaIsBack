import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
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

        ArrayList<String> array_list = new ArrayList<>(List.of("Gunsandroses", "Nirvana", "Metallica", "ACDC"));
        String[] newBands = {"OneDirection", "Rollingstones", "GustavoLima"};
        array_list.add("Ocean");
        array_list.addAll(List.of(newBands));

                    System.out.println("-".repeat(50));

        array_list.sort(Comparator.naturalOrder());

        if(!array_list.contains("naosei")) System.out.println("Yes it not contains");

        System.out.println(array_list);


        groceries.remove(0);
        System.out.println(groceries);

        List<String> names = new ArrayList<>();

        for(int i = 1; i <= 5; i++){
            System.out.printf("\nPrint name %d#:", i);
            String name = sc.nextLine();
            if(names.contains(name)){
                System.out.println("The list already have this name!");
                i--;
            }
            else {
                System.out.println("Okay name added!");
                names.add(name);
            }

        }
        names.sort(Comparator.naturalOrder());
        System.out.println(names);

        String[] array_names = names.toArray(new String[names.size()]);
        for(String i: array_names){
            String trimmed = i.trim();
            System.out.println(trimmed);
        }
        sc.close();
    }
}