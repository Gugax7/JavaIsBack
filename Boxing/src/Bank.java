import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Customer> customers;

    public Bank(String name, ArrayList<Customer> customers) {
        this.name = name;
        this.customers = customers;
    }
    public Bank(String name) {
        this.name = name;
        customers = new ArrayList<Customer>();
    }
    

    public void transactionFromTo(Customer from, Customer to, Double amount){
        checkAndAddIfNew(from);
        checkAndAddIfNew(to);
        from.addTransaction(-amount);
        to.addTransaction(amount);
    }

    public void checkAndAddIfNew(Customer customer){
        if(customers.contains(customer)){
           return;
        }
        customers.add(customer);
    }

    public void printCustomerHistoric(Customer customer){
        checkAndAddIfNew(customer);
        System.out.println("-".repeat(40));
        System.out.println("Name: " + customer.getName());
        System.out.println("Transactions : ");
        customer.listTransactions();

    }

    public void printAllCustomerHistoric(){
        for(Customer c: customers){
            printCustomerHistoric(c);
        }
    }
}
