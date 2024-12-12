import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
    }
    public Customer(String name, ArrayList<Double> transactions) {
        this.name = name;
        this.transactions = transactions;
    }

    public void addTransaction(Double amount){
        transactions.add(amount);
    }
    public void listTransactions(){
        for(Double t: this.transactions){
            System.out.println("--> " + t);
        }
    }

    public String getName() {
        return name;
    }
}
