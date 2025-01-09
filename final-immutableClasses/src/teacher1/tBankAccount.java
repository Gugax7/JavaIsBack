package teacher1;

import teacher1.dto.Transition;

import java.util.*;

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

    public Map<Long, String> getTransactions() {
        Map<Long,String> txMap = new LinkedHashMap<>();
        for(var tx : transactions.entrySet()){
            txMap.put(tx.getKey(), tx.getValue().toString());
        }
        return txMap;
    }
    void commitTransaction(int routingNumber,long transactionId,
                           String customerId, double amount){
        balance+=amount;
        transactions.put(transactionId,new Transition(routingNumber,
                Integer.parseInt(customerId),transactionId,amount));

    }

}
