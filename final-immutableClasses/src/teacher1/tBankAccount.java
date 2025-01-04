package teacher1;

public class tBankAccount {

    public enum AccountType{CHECKING, SAVINGS,INVESTMENTS}
    private final AccountType type;
    private final Double balance;

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
}
