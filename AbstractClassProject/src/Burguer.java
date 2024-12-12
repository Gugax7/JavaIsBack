public class Burguer extends FoodItem {

    private String cheese;
    private String meat;
    private String salad;
    public Burguer(String type, Double price, String description, boolean hasLactose, String cheese, String meat, String salad) {
        super(type, price, description, hasLactose);
        this.cheese = cheese;
        this.meat = meat;
        this.salad = salad;
    }

    @Override
    public void showDetails() {

        System.out.printf("""
                Burguer: %s
                Meat: %s
                Cheese: %s
                Salad: %s
                """, this.getType(), this.meat, this.cheese, this.salad);

    }
}

