import java.util.Map;
import java.util.TreeMap;

public class Maps {

    public static void main(String[] args) {
        TreeMap<String,Integer> goods = new TreeMap<>();
        goods.put("Banana", 30);
        goods.put("Apple", 20);
        goods.put("Mango", 10);

        for(Map.Entry<String,Integer> entry: goods.entrySet()){
            System.out.println("Fruit: " + entry.getKey() + " Quantity: " + entry.getValue());
        }

        System.out.println(goods.lastKey());
    }
}
