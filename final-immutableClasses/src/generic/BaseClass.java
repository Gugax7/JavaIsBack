package generic;

public class BaseClass {
    public final void recommendedMethod(){
        System.out.println("(Base class).recommendedMethod: best way to do it.");
        optionalMethod();
        mandatoryMethod();
    }
    protected void optionalMethod(){
        System.out.println("(Base class).optionalMethod: Customize Optional Method");
    }
    private void mandatoryMethod(){
        System.out.println("Base class.mandatoryMethod: NON NEGOTIABLE!");
    }
    public static void recommendedStatic(){
        System.out.println("Base.recommendedStatic: Best way to do it.");
        optionalStatic();
        mandatoryStatic();
    }
    protected static void optionalStatic(){
        System.out.println("Base.optionalStatic: Optional");
    }
    private static void mandatoryStatic(){
        System.out.println("Base.mandatoryStatic: Mandatory");
    }
}
