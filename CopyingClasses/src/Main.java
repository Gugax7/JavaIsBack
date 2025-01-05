import java.util.Arrays;

record Person(String name, String dob, Person[] kids){
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + "\n" +
                ", dob='" + dob + "\n" +
                ", kids=" + Arrays.toString(kids) +
                '}' + "\n---------------------------------------------\n";
    }
    public Person(Person person){
        this(person.name(), person.dob(),
                person.kids() == null ? null : Arrays.copyOf(person.kids(), person.kids().length));
    }
}
public class Main {
    public static void main(String[] args) {
        Person thomas = new Person("Thomas", "01/11/2011", null);
        Person jack = new Person("Jack", "02/10/2012", null);
        Person john = new Person("John", "03/09/2013", new Person[]{jack,thomas});
        Person mathew = new Person("Mathew", "04/08/2014",null);
        Person alice = new Person("Alice", "05/07/2019", new Person[]{thomas,jack,mathew});

        Person[] persons = {thomas,jack,john,mathew,alice};
        Person[] personsCopy = new Person[persons.length];
        for(int i = 0; i < persons.length; i ++){
            personsCopy[i] = new Person(persons[i]);
        }
        personsCopy[3] = new Person("Jonathan", "02/01/2001",new Person[]{john,alice});

        Person[] kids = personsCopy[4].kids();
        kids[1] = new Person("Armando", "10,10,2010",null);

        System.out.println(Arrays.toString(persons));
        System.out.println();
        System.out.println("\n\n\n\n\n");
        System.out.println(Arrays.toString(personsCopy));



    }
}