//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("Itau");
        Customer arnaldo = new Customer("Arnaldo");
        bank.transactionFromTo(arnaldo, new Customer("Cesar"), 1000.0);
        bank.printAllCustomerHistoric();

        Months month = Months.AUGUST;
        System.out.println(month.ordinal());

    }
}