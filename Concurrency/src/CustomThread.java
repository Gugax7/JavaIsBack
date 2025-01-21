public class CustomThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(" 0 ");
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.err.println("Error on Custom Thread");
            }
        }
    }
}
