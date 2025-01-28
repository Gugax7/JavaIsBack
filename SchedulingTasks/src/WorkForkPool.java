import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class RecursiveSumTask extends RecursiveTask<Long>{
    private final long[] numbers;
    private final int start;
    private final int end;
    private final int division;

    public RecursiveSumTask(long[] numbers, int start, int end, int division) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.division = division;
    }

    @Override
    protected Long compute() {
        if((end - start) <= (numbers.length / division)){
            System.out.println(start + " : " + end);
            long sum = 0;
            for(int i = start; i < end; i++){
                sum+= numbers[i];
            }
            return sum;
        }
        else{
            int mid = (start + end)/2;
            RecursiveSumTask leftTask = new RecursiveSumTask(numbers,start,mid,division);
            RecursiveSumTask rightTask = new RecursiveSumTask(numbers,mid,end,division);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();
        }
    }
}

public class WorkForkPool {

    public static void main(String[] args) throws Exception {
        int numbersLength = 100_000;
        long[] numbers = new Random().longs(numbersLength,1,11).toArray();

        long sum = Arrays.stream(numbers).sum();
        System.out.println("sum = " + sum);

        ForkJoinPool threadForkPool = ForkJoinPool.commonPool();
        ForkJoinPool threadWorkPool = (ForkJoinPool) Executors.newWorkStealingPool(4);


        List<Callable<Long>> tasks = new ArrayList<>();

        int taskNo = 10;
        int splitCount = numbersLength / taskNo;
        for(int i = 0; i < taskNo; i++){
            int start = i*splitCount;
            int end = start + splitCount;
            tasks.add(()->{
                long taskSum = 0;
                for(int j = start; j < end; j++){
                    taskSum+=(long)numbers[j];
                }
                return taskSum;
            });
        }

        System.out.println("--------------------- ForkJoinPool ---------------------");

        List<Future<Long>> futures = threadForkPool.invokeAll(tasks);

        System.out.println("Parallelism: " + threadForkPool.getParallelism());
        System.out.println("PoolSize: " + threadForkPool.getPoolSize());
        System.out.println("Steal count: " + threadForkPool.getStealCount());

        long taskSum = 0;
        for(Future<Long> future : futures){
            taskSum+= future.get();
        }
        System.out.println("Thread pool sum: " + taskSum);

        System.out.println("------------------- WorkStealingPool -------------------");

        List<Future<Long>> futuresWork = threadWorkPool.invokeAll(tasks);

        System.out.println("Parallelism: " + threadWorkPool.getParallelism());
        System.out.println("PoolSize: " + threadWorkPool.getPoolSize());
        System.out.println("Steal count: " + threadWorkPool.getStealCount());

        long taskSumWork = 0;
        for(Future<Long> future : futuresWork){
            taskSumWork+= future.get();
        }


        System.out.println("Thread pool sum: " + taskSumWork);

        System.out.println("------------------- RecursiveSumTask -------------------");

        RecursiveTask<Long> task = new RecursiveSumTask(numbers,0,numbers.length,4);
        long forkJoinSum = threadForkPool.invoke(task);
        System.out.println("Recursive sum is: " + forkJoinSum);
        threadWorkPool.shutdown();
        threadForkPool.shutdown();



    }
}
