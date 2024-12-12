public abstract class FoodItem extends ProductForSale {

    public boolean hasLactose;

    public FoodItem(String type, Double price, String description, boolean hasLactose) {
        super(type, price, description);
        this.hasLactose = hasLactose;
    }
}
