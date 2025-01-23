package syncronization;

public class BankAccount {

    private double balance;

    public BankAccount(double balance){
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
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

        synchronized (this){
            double originalBalance = balance;
            balance += amount;
            System.out.println("Starting balance: " + originalBalance + " DEPOSIT:(" + amount + ")   new balance: " + balance );
        }
    }

}
