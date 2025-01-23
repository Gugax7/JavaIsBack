package syncronization;

public class Main {

    public static void main(String[] args) {
        BankAccount company = new BankAccount(10000.0);

        Thread thread1 = new Thread(()-> company.deposit(5000));
        Thread thread2 = new Thread(()-> company.withdraw(2500));
        Thread thread3 = new Thread(()-> company.deposit(7500));

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(company.getBalance());
    }


}
