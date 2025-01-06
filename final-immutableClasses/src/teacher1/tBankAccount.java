package teacher1;

import teacher1.dto.Transition;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class tBankAccount {

    public enum AccountType{CHECKING, SAVINGS,INVESTMENTS}
    private final AccountType type;
    private Double balance;
    Map<Long, Transition> transactions = new LinkedHashMap<>();

    tBankAccount(AccountType type, Double balance) {
        this.type = type;
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(type,balance);
    }

    public Map<Long, Transition> getTransactions() {
        return Map.copyOf(transactions);
    }
    void commitTransaction(int routingNumber,long transactionId,
                           String customerId, double amount){
        balance+=amount;
        transactions.put(transactionId,new Transition(routingNumber,
                Integer.parseInt(customerId),transactionId,amount));

    }

}
