import java.util.concurrent.TimeUnit;

public class ThreadsBasics {

    public static void main(String[] args) {

        CustomThread customThread = new CustomThread();
        customThread.start();

        Runnable myRunnable = () ->{
            for (int i = 0; i < 4; i++) {
                System.out.println(" 2 ");
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                    System.err.println("Error on my runnable");
                }
            }
        };
        Thread secondThread = new Thread(myRunnable);
        secondThread.start();
        for (int i = 0; i < 3; i++) {
            System.out.println(" 1 ");
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                System.err.println("Error on main thread");
            }
        }


    }
}
