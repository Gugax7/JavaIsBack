import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 2;

    private String name;
    private int rollNumber;
    private int age;
    private String adress;

    public Student(String name, int rollNumber, int age, String address) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.age = age;
        this.adress = address;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int getAge() {
        return age;
    }

    public void displayStudentInfo(){
        System.out.println("Name:" + this.name);
        System.out.println("RollNumber:" + this.rollNumber);
        System.out.println("Age:" + this.age);
    }
}
