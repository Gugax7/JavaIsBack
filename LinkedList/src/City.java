public class City {
   private String name;
   private int distanceFromItatiba;

    public City(String name, int distanceFromItatiba) {
        this.name = name;
        this.distanceFromItatiba = distanceFromItatiba;
    }

    public String getName() {
        return name;
    }

    public int getDistanceFromItatiba() {
        return distanceFromItatiba;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", distanceFromItatiba=" + distanceFromItatiba +
                '}';
    }
}
