package teacher1;

import myown.BankAccount;
import myown.BankCustomer;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    public final int routingNumber;
    private long lastTransactionId = 1;
    private final Map<String,tBankCustomer> customers;

    public Bank(int routingNumber){
        this.routingNumber = routingNumber;
        customers = new HashMap<>();
    }
    public tBankCustomer getCustomer(String id){
        tBankCustomer customer = customers.get(id);
        return customer;
    }

    public void addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit, double investmentsInitialDeposit){
        tBankCustomer customer = new tBankCustomer(name,checkingInitialDeposit,savingsInitialDeposit,investmentsInitialDeposit);
        customers.put(customer.getCustomerId(),customer);

    }

    public boolean doTransaction(String id,tBankAccount.AccountType type, double amount){
        tBankCustomer customer = customers.get(id);
        if(customer != null){
            tBankAccount account = customer.getAccount(type);
            if((account.getBalance() + amount < 0)){
                System.err.println("Insufficient funds");
            }else{
                account.commitTransaction(routingNumber,lastTransactionId++,id,amount);
                return true;
            }
        }else{
            System.err.println("Invalid Customer id!");
        }

        return false;
    }


}
