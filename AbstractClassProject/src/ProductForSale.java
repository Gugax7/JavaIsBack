public abstract class ProductForSale {
    private String type;
    private Double price;
    private String description;

    public ProductForSale(String type, Double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public Double getSalesPrice(Integer quantity) {
        return quantity * price;
    }

    public void printSalesPrice(int quantity){
        System.out.printf("%2d unities at %8.2f each, %-15s %-35s \n", quantity,price,type,description);
    }


    public abstract void showDetails();

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}