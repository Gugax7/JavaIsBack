import myown.BankAccount;
import myown.BankCustomer;
import teacher1.tBankAccount;
import teacher1.tBankCustomer;

import java.util.List;

public class myOwnMain {
    public static void main(String[] args) {
//        tBankAccount account = new tBankAccount(tBankAccount.AccountType.CHECKING, 1500.0);
//        System.out.println(account);
        tBankCustomer joe = new tBankCustomer("Joe", 1000.0,2000.0,3000.0);
        System.out.println(joe);
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