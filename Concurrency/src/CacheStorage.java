import java.util.concurrent.TimeUnit;

public class CacheStorage {
    private volatile boolean flag = false;

    public void toggleFlag(){
        flag = !flag;
    }
    public boolean isReady(){
        return flag;
    }
    public static void main(String[] args) {
        CacheStorage example = new CacheStorage();
        Thread writerThread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            example.toggleFlag();
            System.out.println("Flag set to: " + example.isReady());
        });

        Thread readerThread = new Thread(()->{
            while(!example.isReady()){
                //just waiting for my previous thread
            }
            System.out.println("Flag is: " + example.isReady());
        });

        writerThread.start();
        readerThread.start();
    }
}
