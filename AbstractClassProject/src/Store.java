import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Store {
    private static ArrayList<ProductForSale> storeProducts = new ArrayList<>();

    public static void main(String[] args) {

        storeProducts.add(new Burguer("Burguer", 10.0,"Good burguer with good ingredients",
                true, "Switzzernsht", "Picanha", "Lettuce + Tommato"));
        storeProducts.add(new Computer("ComputerGamer", 1000.0,"PC FOR GAMERS", 9,"DELL","intel i5", "RTX 4060", "600W"));

        var order1 = new ArrayList<OrderItem>();
        addItemToOrder(order1,1,3);
        addItemToOrder(order1,0,10);

        printOrder(order1);
    }
    public static void addItemToOrder(ArrayList<OrderItem> order, int index, int quantity){
        order.add(new OrderItem(storeProducts.get(index), quantity));
    }

    public static void printOrder(ArrayList<OrderItem> order){
        for(OrderItem item : order){
            System.out.println("-".repeat(40));
            item.getItem().showDetails();
            item.getItem().printSalesPrice(item.getQuantity());
        }
    }
}