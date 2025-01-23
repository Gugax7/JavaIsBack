package challenge;

public class myThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(((i+1) * 2) + " --- " + currentThread().getState());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
