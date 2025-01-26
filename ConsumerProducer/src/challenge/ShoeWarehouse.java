package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {
    private final ExecutorService fulfillmentService;
    public static List<String> products = new ArrayList<>(List.of("Flat shoes", "Big shoes", "Expensive shoes"));
    private List<Order> orders = new ArrayList<>();
    private final int listMaxCapacity = 10;

    public ShoeWarehouse(){
        fulfillmentService = Executors.newFixedThreadPool(3);

    }
    public void shutDown(){
        fulfillmentService.shutdown();
    }

    public synchronized void receiveOrder(Order order){
        while(orders.size() >= listMaxCapacity){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        orders.add(order);
        //System.out.println(Thread.currentThread().getName() + "    --->   Order added!");
        fulfillmentService.submit(this::fulfillOrder);
        notifyAll();
    }

    public synchronized void fulfillOrder(){
        while(orders.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order toFulfill = orders.get(orders.size() - 1);
        System.out.println(Thread.currentThread().getName() + "    --->     Order fulfilled! ->" + toFulfill.getId());
        orders.remove(toFulfill);
        notifyAll();
    }
}
