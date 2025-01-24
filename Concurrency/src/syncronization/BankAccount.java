package syncronization;

public class BankAccount {

    private double balance;
    private String name;

    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public BankAccount(String name, double balance){
        this.balance = balance;
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }
    public void setName(String name){
        synchronized (lockName){
            System.out.println("Setting name from : " + this.name + " to : " + name);
            this.name = name;
        }
    }

    public synchronized void withdraw(double amount) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double originalBalance = balance;
        if (originalBalance >= amount) {
            balance -= amount;
            System.out.println("Starting balance: "
                    + originalBalance + " WITHDRAW:(" + amount
                    + ")   new balance: " + balance);
        }
        else {
            System.out.println("Starting balance: "
                    + originalBalance + " WITHDRAW:(" + amount
                    + ")   INSUFFICIENT FUNDS");
        }
    }
    public void deposit(double amount){
        try{
            System.out.println("Talking to the beautiful teller at the bank");
            Thread.sleep(7000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (lockBalance){
            double originalBalance = balance;
            balance += amount;
            System.out.println("Starting balance: " + originalBalance + " DEPOSIT:(" + amount + ")   new balance: " + balance );
            addPromotional(amount);
        }
    }
    private void addPromotional(double value){
        if(value >= 5000.0){
            synchronized (lockBalance) {
                System.out.println("Congrats you've won promotional amount on your account");
                balance+=25.0;
            }
        }
    }


}
