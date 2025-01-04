package immutable_classes;

public class Main {
    public static void main(String[] args) {
        Person jane = new Person();
        jane.setName("Jane");
        Person jim = new Person();
        jim.setName("Jim");
        Person joe = new Person();
        joe.setName("Joe");

        Person john = new Person();
        john.setName("John");
        john.setDob("10/10/1010");
        john.setKids(new Person[]{jane,jim,joe});

        System.out.println(john.toString());
    }
}
