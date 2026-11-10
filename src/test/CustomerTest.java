package test;

import main.BankAccount;
import main.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void testCreateMultipleAccounts() {
        Customer customer = new Customer("Alex");

        BankAccount firstAccount = customer.createAccount();
        BankAccount secondAccount = customer.createAccount();

        assertEquals(2, customer.getNumberOfAccounts());
        assertSame(firstAccount, customer.getAccount(0));
        assertSame(secondAccount, customer.getAccount(1));
        assertNotSame(firstAccount, secondAccount);
    }

    @Test
    public void testCustomersHaveSeparateAccounts() {
        Customer firstCustomer = new Customer("Alex");
        Customer secondCustomer = new Customer("Eric");

        BankAccount firstAccount = firstCustomer.createAccount();
        BankAccount secondAccount = secondCustomer.createAccount();
        firstAccount.deposit(50);
        secondAccount.deposit(100);

        assertEquals(1, firstCustomer.getNumberOfAccounts());
        assertEquals(1, secondCustomer.getNumberOfAccounts());
        assertNotSame(firstAccount, secondAccount);
        assertEquals(50, firstCustomer.getAccount(0).getBalance(), 0.01);
        assertEquals(100, secondCustomer.getAccount(0).getBalance(), 0.01);
    }
}
