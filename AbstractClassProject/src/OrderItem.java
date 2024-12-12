public class OrderItem {
    private ProductForSale item;
    private int quantity;

    public OrderItem(ProductForSale item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ProductForSale getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
