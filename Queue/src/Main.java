import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Queue<Integer> queuePriority = new PriorityQueue<>();
        queuePriority.add(50);
        queuePriority.add(40);
        queuePriority.add(10);
        queuePriority.add(20);
        queuePriority.add(30);
        queuePriority.add(5);
        queuePriority.add(0);
        queuePriority.add(-10);

        System.out.println(queuePriority);

        ArrayDeque<String> names = new ArrayDeque<>();
        names.add("Pedro");
        names.add("Gustavo");
        names.add("Diego");
        names.add("Gatt");
        names.add("Pedro");
        
        System.out.println(names.poll());
        System.out.println(names.poll());
        System.out.println(names.poll());
        System.out.println(names.poll());
        System.out.println(names);
    }
}