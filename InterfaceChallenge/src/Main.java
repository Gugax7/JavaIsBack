import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Mappable> mappables = new ArrayList<>();
        mappables.add(new Building("Stadium",UsageType.SPORTS));
        mappables.add(new Building("Circus",UsageType.ENTERTAINMENT));
        mappables.add(new Building("Pentagon",UsageType.GOVERNMENTAL));
        mappables.add(new Building("My house",UsageType.RESIDENTIAL));

        for(var m: mappables){
            Mappable.mapIt(m);
        }
    }
}