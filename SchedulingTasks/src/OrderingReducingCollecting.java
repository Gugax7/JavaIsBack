import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Person(String firstName, String lastName, int age){
     private final static String[] firsts = {"Able","Bob", "Charlie", "Donna", "Eve", "Fred"};
     private final static String[] lasts = {"Norton", "Ohara" ,"Salmazo", "Burton", "Silva"};

     private final static Random rand = new Random();

     public Person(){
         this(
                 firsts[rand.nextInt(firsts.length)],
                 lasts[rand.nextInt(lasts.length)],
                 rand.nextInt(18,99)
         );
     }

    @Override
    public String toString() {
        return "%s, %s (%d)".formatted(lastName,firstName,age);
    }
}

public class OrderingReducingCollecting {

    public static void main(String[] args) {

        var people = Stream.generate(Person::new)
                        .limit(10)
                                .sorted(Comparator.comparing(Person::firstName))
                                        .toArray();

        Arrays.stream(people)
                .limit(10)
                .parallel()
                .forEachOrdered(System.out::println);


        System.out.println("-".repeat(30));

        int sum = IntStream.range(1,101)
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println("Sum of integers = " + sum);

        String exitCar = """
                Enter car, adjust the seat, the mirrors
                and put the belt, step on breaks and embreagem,
                turn on the car, push hand break, first gear
                left arrow, look at the left mirror, and exit.""";
        System.out.println("-".repeat(30));
        var words = new Scanner(exitCar).tokens().toList();
        words.forEach(System.out::println);

        var backTogether = words.parallelStream()
                .collect(Collectors.joining(" "));

        System.out.println(backTogether);

        Map<String, Long> lastNameCounts =
                Stream.generate(Person::new)
                        .limit(10000)
                        .parallel()
                        .collect(Collectors.groupingByConcurrent(
                                Person::firstName,
                                Collectors.counting()
                        ));

        System.out.println("-".repeat(30));

        lastNameCounts.entrySet().forEach(System.out::println);

        var lastCounts = new ConcurrentSkipListMap<String,Long>();
        Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .forEach((person) -> lastCounts.merge(person.lastName(), 1L,Long::sum));

        System.out.println("-------------------------");
        System.out.println(lastCounts);
    }
}
