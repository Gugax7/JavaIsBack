import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Stream;

public class MainLists {

    public static void main(String[] args) {
        var threadMap = new ConcurrentSkipListMap<String, Long>();

        var persons = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .peek(p -> {
                    var threadName = Thread.currentThread().getName().replace("ForkJoinPool.commonPool-worker-", "thread_");
                    threadMap.merge(threadName,1L, Long::sum);
                })
                .toArray(Person[]::new);
        System.out.println("Total = " + persons.length);

        long total = 0;
        for(var counts : threadMap.values()){
            total+=counts;
        }
        System.out.println(threadMap);
        System.out.println("Total Thread counts =" + total);

    }
}
