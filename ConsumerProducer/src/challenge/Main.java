package challenge;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ShoeWarehouse house = new ShoeWarehouse();
        Random rand = new Random();
        ExecutorService orderingService = Executors.newCachedThreadPool();
        Callable<Order> orderingTask = () ->{
            Order newOrder = generateOrder();
            try{
                Thread.sleep(rand.nextInt(500,2000));
                house.receiveOrder(newOrder);
            }catch (InterruptedException e){
                System.err.println("Exception on receiving the order");
            }
            return newOrder;
        };

        List<Callable<Order>> tasks = Collections.nCopies(15, orderingTask);
        try {
            orderingService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        orderingService.shutdown();
        try {
            orderingService.awaitTermination(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        house.shutDown();

//        Thread producer = new Thread(() -> {
//            Random rand = new Random();
//            for(int i = 0; i < 30; i++){
//                house.receiveOrder(new Order(ShoeWarehouse.products.get(rand.nextInt(0,3)), rand.nextInt(10)));
//            }
//        });
//
//        Thread consumer = new Thread(()->{
//            while(producer.isAlive()){
//                house.fulfillOrder();
//            }
//        });
//
//        producer.start();
//        consumer.start();
    }

    private static Order generateOrder(){
        Random rand = new Random();
        return new Order(ShoeWarehouse.products.get(rand.nextInt(0,3)), rand.nextInt(10));
    }
}
