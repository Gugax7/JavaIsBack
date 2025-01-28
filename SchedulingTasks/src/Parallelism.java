import java.util.Arrays;
import java.util.Random;

public class Parallelism {

    public static void main(String[] args) {

        long numbersSize = 100_000_000;
        long[] numbers = new Random().longs(numbersSize,1,100).toArray();

        long start = System.nanoTime();
        double averageSerial = Arrays.stream(numbers).average().orElseThrow();
        long elapsedSerial = System.nanoTime() - start;
        System.out.printf("Ave = %.2f, elapsed = %d nanos or %.2f ms\n", averageSerial,elapsedSerial,elapsedSerial/1000000.0);

        start = System.nanoTime();
        double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
        long elapsedParallel = System.nanoTime() - start;
        System.out.printf("Ave = %.2f, elapsed = %d nanos or %.2f ms\n", averageParallel,elapsedParallel,elapsedParallel/1000000.0);

    }
}
