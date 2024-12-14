enum UsageType{ENTERTAINMENT, RESIDENTIAL, GOVERNMENTAL, SPORTS}
public class Building implements Mappable{
    private String name;
    private UsageType usage;

    public Building(String name, UsageType usage) {
        this.name = name;
        this.usage = usage;
    }

    @Override
    public String getLabel() {
        return name + " (" + usage + ")";
    }

    @Override
    public Geometry getShape() {
        return Geometry.POINT;
    }

    @Override
    public String getMarker() {
        return switch (usage){
            case ENTERTAINMENT -> Color.GREEN + " " + PointMarker.STAR;
            case GOVERNMENTAL -> Color.BLACK + " " + PointMarker.SQUARE;
            case SPORTS -> Color.RED + " " + PointMarker.CIRCLE;
            case RESIDENTIAL -> Color.ORANGE + " " + PointMarker.SQUARE;
            default -> Color.BLUE + " " + PointMarker.PUSH_PIN;
        };
    }
}
