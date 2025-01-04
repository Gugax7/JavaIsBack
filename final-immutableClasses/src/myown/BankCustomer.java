package myown;

import java.util.List;

public class BankCustomer {
    private final String name;
    private final Integer id;
    protected final List<BankAccount> accounts;

    public BankCustomer(String name, Integer id, List<BankAccount> accounts) {
        this.name = name;
        this.id = id;
        this.accounts = accounts == null ? null : List.copyOf(accounts);
    }

    protected BankCustomer(BankCustomer bankCustomer){
        this(bankCustomer.getName(), bankCustomer.getId(), bankCustomer.getAccounts());
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public List<BankAccount> getAccounts() {
        return accounts == null ? null : List.copyOf(accounts);
    }

    @Override
    public String toString() {
        return "Customer: " + getName() +
                "\nID: " + getId() +
                "\nAccounts: " + getAccounts().toString();
    }
}
