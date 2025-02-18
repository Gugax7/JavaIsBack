package banking_junit;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BankAccountParamitezedTest {
    private BankAccount account;
    private double amount;
    private boolean branch;
    private double expected;

    public BankAccountParamitezedTest(double amount, boolean branch, double expected) {
        this.amount = amount;
        this.branch = branch;
        this.expected = expected;

    }

    @org.junit.Before
    public void setup(){
        account = new BankAccount("Gustavo", "Salmazo",300.00);
        System.out.println("Running a test...");
    }

    @Parameterized.Parameters
    public static Collection<Object[]> setParameters(){
        return Arrays.asList(new Object[][]{
                {100,true,400},
                {222.22, true, 522.22},
                {1000.14, true, 1300.14},
                {1000.00, true, 1300.00}
        });
    }
    @org.junit.Test
    public void deposit() throws Exception{
        account.deposit(amount, branch);
        assertEquals(expected,account.getBalance(),0.0001);
    }
}
