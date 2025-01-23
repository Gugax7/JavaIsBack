public class InteractingWith {

    public static void main(String[] args) {
        System.out.println("Main thread is running");
        try {
            System.out.println("Main thread paused for a sec");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread thread = new Thread(() -> {
            String tname = Thread.currentThread().getName();
            System.out.println(tname + " should take 10 dots to run");
            for (int i = 0; i < 10; i++) {
                try{
                    System.out.print(" . ");
                    Thread.sleep(500);
                    System.out.println("Current thread state = " + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    System.err.println("Whoops, " + tname + " was interrupted.");
                    return;
                }

            }

            System.out.println(tname + " completed!");
        });
        System.out.println(thread.getName() + " starting now!");
        thread.start();

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("Error on sleep main thread");
        }

        //thread.interrupt();

        System.out.println("Main thread continue here");
        System.out.println("A. Thread state: " + thread.getState());
        
        long now = System.currentTimeMillis();
        while(thread.isAlive()){
            System.out.println("\nwaiting for thread to complete");
            try{
                Thread.sleep(1000);
                System.out.println("B. State = " + thread.getState());

                if(System.currentTimeMillis() - now > 2000){
                    thread.interrupt();
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
