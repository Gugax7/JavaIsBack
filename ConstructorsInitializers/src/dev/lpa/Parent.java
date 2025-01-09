package dev.lpa;

public class Parent {

    private final String name;
    private final String dob;
    protected final int siblings;


    {
        System.out.println("Initializer action");
    }

//    public Parent() {
//        System.out.println("Empty constructor action");
//    }

    public Parent(String name, String dob, int siblings) {
        this.name = name;
        this.dob = dob;
        this.siblings = siblings;
        System.out.println("Two arguments constructor action");
    }

    public String getName() {
        return name;
    }


    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
