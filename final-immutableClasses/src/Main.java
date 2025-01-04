import consumer.specific.ChildClass;
import external.util.Logger;
import generic.BaseClass;

public class Main {

    public static void main(String[] args) {
        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredAsToBase = new ChildClass();
        System.out.println("_".repeat(50));
        child.recommendedMethod();
        System.out.println("_".repeat(50));
        parent.recommendedMethod();
        System.out.println("_".repeat(50));
        childReferredAsToBase.recommendedMethod();
        System.out.println("_".repeat(50));

        System.out.println("_".repeat(50));
        child.recommendedStatic();
        System.out.println("_".repeat(50));
        parent.recommendedStatic();
        System.out.println("_".repeat(50));
        childReferredAsToBase.recommendedStatic();
        System.out.println("_".repeat(50));

        String xArgument = "Thats all i have to tell about section ";
        doXYZ(xArgument,16);

        StringBuilder tracker = new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker);
        tracker.append(" Step 2 is xyz");
        Logger.logToConsole(tracker);
        System.out.println("After logging, tracker = " + tracker.toString());
    }

    private static void doXYZ(String x, int y){
        final String c = x + y;
        System.out.println("c = " + c);
    }

}