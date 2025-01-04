package consumer.specific;

import generic.BaseClass;

public class ChildClass extends BaseClass {
    @Override
    protected void optionalMethod() {
        super.optionalMethod();
        System.out.println("Child.optionalMethod: EXTRA STUFF HERE");
    }

    private void mandatoryMethod(){
        System.out.println("Child.MandatoryMethod: My own important stuff");
    }
    public static void recommendedStatic(){
        System.out.println("Child.recommendedStatic: Best way to do it.");
        optionalStatic();
        //mandatoryStatic();
    }
}
