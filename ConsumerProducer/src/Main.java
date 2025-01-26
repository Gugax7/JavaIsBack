import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageRepository{
    private String message;
    private boolean hasMessage = false;
    private final Lock lock = new ReentrantLock();

    public String read(){
        if(lock.tryLock()) {
            try {
                //System.out.println("Waiting for my loop ends, HasMessage = " + hasMessage + " and needs to be true");
                while (!hasMessage) {
                    // just waiting for message be read
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;
            } finally {
                lock.unlock();
            }
        }
        else{
            System.out.println("read is blocked**");
            hasMessage = false;
        }
        return message;
    }

    public synchronized void write(String message){
        if(lock.tryLock()) {
           try { //System.out.println("Waiting for my loop ends, HasMessage = " + hasMessage + " and needs to be false");
               while (hasMessage) {
                   // just waiting for message be read
                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
               hasMessage = true;
           }finally {
               lock.unlock();
           }
        }else{
            hasMessage = true;
            System.out.println("**write is blocked");
        }
        this.message = message;
    }


}

class MessageWriter implements Runnable {
    private MessageRepository outgoingMessage;

    private final String text = """
            Enter the car, adjust the seat
            adjust the mirrors and put the belt.
            Press breaks and embreagem, turn on the car;
            pull hand-break, put on first gear, left arrow.
            look to left mirror and can left exit the perch.
            """;

    public MessageWriter(MessageRepository outgoingMessage){
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {
        Random rand = new Random();
        String[] lines = text.split("\n");

        for(int i = 0; i < lines.length; i++){
            //System.out.println("Running (writing)");
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(rand.nextInt(500,2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        outgoingMessage.write("Finished!");
    }
}

class MessageReader implements Runnable{
    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }


    @Override
    public void run() {
        Random rand = new Random();
        String lastMessage = "";

        do{
            //System.out.println("Running (reading)");
            try {
                Thread.sleep(rand.nextInt(500,2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lastMessage = incomingMessage.read();
            System.out.println(lastMessage);
        }while(!lastMessage.equals("Finished!"));
    }
}

public class Main {
    public static void main(String[] args) {
        MessageRepository repository = new MessageRepository();

        Thread writer = new Thread(new MessageWriter(repository));
        Thread reader = new Thread(new MessageReader(repository));

        reader.start();
        writer.start();

        writer.setUncaughtExceptionHandler((thread,exp)->{
            System.out.println("Writer has an exception: " + exp);
            if(reader.isAlive()){
                System.out.println("Interrupting reader");
                reader.interrupt();
            }
        });
        reader.setUncaughtExceptionHandler((thread,exp)->{
            System.out.println("Reader has an exception: " + exp);
            if(writer.isAlive()){
                System.out.println("Interrupting writer");
                writer.interrupt();
            }
        });
    }
}