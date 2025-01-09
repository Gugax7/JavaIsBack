import myown.BankAccount;
import myown.BankCustomer;
import teacher1.Bank;
import teacher1.dto.Transition;
import teacher1.tBankAccount;
import teacher1.tBankCustomer;

import java.util.List;

public class myOwnMain {
    public static void main(String[] args) {
        Bank bank = new Bank(1093491);
        bank.addCustomer("Joe", 1000,1000,1000);
        tBankCustomer joe = bank.getCustomer("000000010000000");
        System.out.println(joe);

        if(bank.doTransaction(joe.getCustomerId(),
                tBankAccount.AccountType.CHECKING,
                -1000)) System.out.println(joe);
        tBankAccount checking = joe.getAccount(tBankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((v,u) -> System.out.println(v + " : " + u.toString()));

        //transactions.put(3L, new Transition(1,Integer.parseInt(joe.getCustomerId()), 1, 600));

//        for(var tx: transactions.values()){
//            tx.setCustomerId(2);
//            tx.setAmount(1000.0);
//        }
        transactions.forEach((k,v) -> System.out.println(k + ": " + v));
    }


}




//BankCustomer customer1 = new BankCustomer("Angelo", 0, List.of(
//        new BankAccount("SavingsAccount", 2000.0),
//        new BankAccount("InvestmentsAccount", 434.8),
//        new BankAccount("RegularAccount", 1600.0)
//));
//
//List<BankAccount> myNewList = customer1.getAccounts();
////myNewList.add(new BankAccount("SavingsAccount", 1400.0));
//        System.out.println(customer1.toString());