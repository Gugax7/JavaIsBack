public class Computer extends TechnologyItem {

    private String core;
    private String videoCard;
    private String source;
    public Computer(String type, Double price, String description, Integer generation, String manufacturer, String core, String videoCard, String source) {
        super(type, price, description, generation, manufacturer);
        this.core = core;
        this.source = source;
        this.videoCard = videoCard;
    }

    @Override
    public void showDetails() {

        System.out.printf("""
                Computer: %s
                Core: %s
                Video card: %s
                Source: %s
                """, this.getType(), this.core, this.videoCard, this.source);

    }
}
