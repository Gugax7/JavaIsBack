package banking_junit;

import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {


    @org.junit.jupiter.api.Test
    void deposit() {
        BankAccount dummyAccount = new BankAccount("duh", "mie",1000.00);
        double balance = dummyAccount.deposit(200.00,true);
        assertEquals(1200.00,balance,0);
        assertEquals(1200.00, dummyAccount.getBalance(),0);
    }

    @org.junit.jupiter.api.Test
    void withdraw() {
        fail("This method is not yet implemented");
    }

    @org.junit.jupiter.api.Test
    void getBalance() {
    }

}