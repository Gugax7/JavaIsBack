import java.util.List;
import java.util.concurrent.*;

class ColorThreadFactory implements ThreadFactory{

    private String threadName;
    private int colorValue = 1;
    public ColorThreadFactory(ThreadColor color){
        this.threadName = color.name();
    }

    public ColorThreadFactory() {

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = threadName;
        if(name == null){
            name = ThreadColor.values()[colorValue].name();
        }
        if(++colorValue > ThreadColor.values().length - 1){
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }
}

public class Main {

    public static void main(String[] args) {
        var multiExecutor = Executors.newCachedThreadPool();
        try{
            List<Callable<Integer>> taskList = List.of(
                    ()->Main.sum(1,10,1,"blue"),
                    ()->Main.sum(-100,0,10,"red"),
                    ()->Main.sum(0,1000,2,"purple")
            );
            try {
                var results = multiExecutor.invokeAll(taskList);
                for(var result : results){
                    System.out.println(result.get(500,TimeUnit.MILLISECONDS));
                }
            }catch(InterruptedException | TimeoutException |ExecutionException e){
                throw new RuntimeException(e);
            }
        }finally {
            multiExecutor.shutdown();
        }
    }

    public static void cachedmain(String[] args) {
        var multiExecutor = Executors.newCachedThreadPool();
        try{
            multiExecutor.submit(() -> {
                Main.sum(1,10,1,"red");
            });
            multiExecutor.submit(() -> {
                Main.sum(10,100,10,"blue");
            });
            multiExecutor.submit(() -> {
                Main.sum(100,1000,1000,"purple");
            });
        }finally {
            multiExecutor.shutdown();
        }
    }
    public static void fixedmain(String[] args) {
        int count = 8;
        var multiExecutor = Executors.newFixedThreadPool(
                count,new ColorThreadFactory()
        );

        for(int i = 0; i < count; i++){
            multiExecutor.execute(Main::countDown);
        }
        multiExecutor.shutdown();
    }
    public static void singlemain(String[] args) {
        var blueExecutor = Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_BLUE)
        );
        blueExecutor.execute(Main::countDown);
        blueExecutor.shutdown();

        boolean isDone = false;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        var blackExecutor = Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_BLACK)
        );
        blackExecutor.execute(Main::countDown);
        blackExecutor.shutdown();

        try {
            blackExecutor.awaitTermination(500,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var yellowExecutor = Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_YELLOW)
        );
        yellowExecutor.execute(Main::countDown);
        yellowExecutor.shutdown();
    }
    public static void notmain(String[] args) {

        Thread cyan = new Thread(Main::countDown, ThreadColor.ANSI_CYAN.name());
        Thread red = new Thread(Main::countDown, ThreadColor.ANSI_RED.name());
        Thread purple = new Thread(Main::countDown, ThreadColor.ANSI_PURPLE.name());

        cyan.start();
        red.start();
        purple.start();

        try{
            cyan.join();
            red.join();
            purple.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void countDown(){

        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;
        try{
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        }catch (IllegalArgumentException e){
            // do nothing
        }

        String color = threadColor.color();
        for(int i = 20; i >= 0; i--){
            System.out.println(color + " " + threadName.replace("ANSI_","") + " " + i);
        }
    }

    private static int sum(int start, int end, int delta, String colorString){
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = threadColor.valueOf("ANSI_"
                    + colorString.toUpperCase());

        }catch(IllegalArgumentException ignore){
            // ignoring mistakes at inputs.
        }

        String color = threadColor.color();
        int sum = 0;
        for(int i = start; i <= end; i += delta){
            sum+=i;
        }
        System.out.println(color + Thread.currentThread().getName() + ", "
        + colorString + " " + sum);

        return sum;

    }
}