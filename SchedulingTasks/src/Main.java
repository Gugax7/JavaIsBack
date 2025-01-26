import java.lang.invoke.CallSite;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        var dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.LONG
        );
        Callable<ZonedDateTime> whenThenDoIt = () -> {
          ZonedDateTime zdt = null;
          try{
              TimeUnit.SECONDS.sleep(2);
              zdt = ZonedDateTime.now();
          }catch (InterruptedException e){
              throw new RuntimeException(e);
          }
          return zdt;
        };
        var threadPool = Executors.newFixedThreadPool(1);
        List<Callable<ZonedDateTime>> list = Collections.nCopies(4,whenThenDoIt);

        try{
            System.out.println("using sleep ===> " + ZonedDateTime.now().format(dtf));
            List<Future<ZonedDateTime>> futureList = threadPool.invokeAll(list);
            for(Future<ZonedDateTime> result : futureList){
                System.out.println(result.get(1,TimeUnit.SECONDS).format(dtf));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

        Runnable dateTask = ()->{
            try{
                TimeUnit.SECONDS.sleep(3);
                System.out.println(ZonedDateTime.now().format(dtf));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        System.out.println("fixed delay ===> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        var scheduledTask = executor.scheduleWithFixedDelay(
                dateTask,
                2,
                2,
                TimeUnit.SECONDS);
        var time = System.currentTimeMillis();
        while(!scheduledTask.isDone()){
            try{
                TimeUnit.SECONDS.sleep(2);
                if((System.currentTimeMillis() - time) /1000 > 10){
                    scheduledTask.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        dateTask = ()->{
            try{
                TimeUnit.SECONDS.sleep(3);
                System.out.println(ZonedDateTime.now().format(dtf));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        System.out.println("fixed rate ===> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executorWithRate = Executors.newScheduledThreadPool(4);
        var scheduledTaskWithRate = executorWithRate.scheduleAtFixedRate(
                dateTask,
                2,
                2,
                TimeUnit.SECONDS);
        time = System.currentTimeMillis();
        while(!scheduledTaskWithRate.isDone()){
            try{
                TimeUnit.SECONDS.sleep(2);
                if((System.currentTimeMillis() - time) /1000 > 10){
                    scheduledTaskWithRate.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executorWithRate.shutdown();
        executor.shutdown();
    }
}