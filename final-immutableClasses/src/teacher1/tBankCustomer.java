package teacher1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tBankCustomer {
    public static int lastCustomerId = 10_000_000;
    private final String name;
    private final int customerId;
    private final List<tBankAccount> accounts = new ArrayList<>();

    public tBankCustomer(String name, double checkingAmount, double savingsAmount, double investmentsAmount) {
        this.name = name;
        this.customerId = lastCustomerId++;
        accounts.add(new tBankAccount(tBankAccount.AccountType.SAVINGS,savingsAmount));
        accounts.add(new tBankAccount(tBankAccount.AccountType.CHECKING,checkingAmount));
        accounts.add(new tBankAccount(tBankAccount.AccountType.INVESTMENTS,investmentsAmount));

    }

    public String getName() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<tBankAccount> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public String toString(){
        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%015d)%n\t%s%n".formatted(name,customerId,String.join("\n\t", accountStrings));
    }
}
