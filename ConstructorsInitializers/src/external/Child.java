package external;

import dev.lpa.Parent;

import java.util.Random;



public class Child extends Parent {
    private final int birthOrder = getBirthOrder();
    private final String birthOrderString;

    {
        if(siblings == 0) birthOrderString = "Only";
        else if (birthOrder == 1) birthOrderString = "First";
        else if (birthOrder == siblings + 1) birthOrderString = "Last";
        else birthOrderString = "Middle";
    }

    public Child() {
        super("GULPING", "sNORLAX", 3);
    }

    public final int getBirthOrder(){
        if (siblings == 0) return 1;
        return new Random().nextInt(1,siblings+2);
    }

    @Override
    public String toString() {
        return super.toString() +
                "birthOrder=" + birthOrder +
                ", birthOrderString='" + birthOrderString + '\'' +
                '}';
    }
}
