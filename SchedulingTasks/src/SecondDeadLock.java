import java.io.File;

public class SecondDeadLock {
    public static void main(String[] args) {
        File resourceA = new File("inputData.csv");
        File resourceB = new File("outputData.json");

        System.out.println("Is running!");

        Thread threadA = new Thread(() -> {
           String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceA (csv)");
            synchronized (resourceA){
                System.out.println(threadName + " has locked resourceA (csv)");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(threadName +
                        " NEXT attempting to lock resourceB (json), " +
                        "still has lock on resourceA (csv)");
                synchronized (resourceB){
                    System.out.println(threadName + " has lock on resourceB (json)");
                }
                System.out.println(threadName + " has released lock on resourceB (json)");
            }
            System.out.println(threadName + " has released lock on resourceA (csv)");
        }, "THREAD-A");

        Thread threadB = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceA (csv)");
            synchronized (resourceA){
                System.out.println(threadName + " has locked resourceA (csv)");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(threadName +
                        " NEXT attempting to lock resourceB (json), " +
                        "still has lock on resourceA (csv)");
                synchronized (resourceB){
                    System.out.println(threadName + " has lock on resourceB (json)");
                }
                System.out.println(threadName + " has released lock on resourceB (json)");
            }
            System.out.println(threadName + " has released lock on resourceA (csv)");
        }, "THREAD-B");

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
