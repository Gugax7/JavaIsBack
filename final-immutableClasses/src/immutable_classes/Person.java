package immutable_classes;

import java.util.Arrays;

public class Person {
    private String name;
    private String dob;
    private Person[] kids;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Person[] getKids() {
        return kids;
    }

    public void setKids(Person[] kids) {
        this.kids = kids;
    }

    @Override
    public String toString() {

        String kidsString = "[ ";
        for(Person kid: kids){
            kidsString+=kid.getName() + " ";
        }
        kidsString+="]";
        return "-".repeat(30) + "\n" +
                "name=" + name + '\n' +
                "dob=" + dob + '\n' +
                "kids=" + kidsString;
    }
}
