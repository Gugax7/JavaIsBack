package banking_junit;

public class BankAccount {

    private String firstName;
    private String lastName;
    private double balance;

    public BankAccount(String firstName, String lastName, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    //the branch argument is true when the customer is
    //doing the transaction through a teller.
    //otherwise if he is doing through an ATM, its false
    public Double deposit(Double amount, boolean branch){
        balance+=amount;
        return balance;
    }
    //the branch argument is true when the customer is
    //doing the transaction through a teller.
    //otherwise if he is doing through an ATM, its false
    public Double withdraw(Double amount, boolean branch){
        balance-= amount;
        return balance;
    }

    public Double getBalance(){
        return balance;
    }
}
