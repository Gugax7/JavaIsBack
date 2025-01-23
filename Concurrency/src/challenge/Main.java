package challenge;

public class Main {
    public static void main(String[] args) {
        myThread myThread = new myThread();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println((2 * i + 1) + " --- " + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    System.err.println("odd numbers was interrupted");
                    return;
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        myThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread.interrupt();
    }
}