package challenge;

public class Order {

    private int id;
    private String shoeType;
    private int quantity;
    private static int current_id = 0;
    public Order(String shoeType, int quantity){
        this.id = ++current_id;
        this.shoeType = shoeType;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getShoeType() {
        return shoeType;
    }

    public int getQuantity() {
        return quantity;
    }
}
