public abstract class TechnologyItem extends ProductForSale{

    private Integer generation;
    private String manufacturer;

    public TechnologyItem(String type, Double price, String description,Integer generation, String manufacturer) {
        super(type, price, description);
        this.generation = generation;
        this.manufacturer = manufacturer;
    }

}
