package test;

import main.Bank;
import main.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class BankTest {

    @Test
    public void testAddMultipleCustomers() {
        Bank bank = new Bank();

        Customer firstCustomer = bank.addCustomer("Eric");
        Customer secondCustomer = bank.addCustomer("Alex");
        Customer thirdCustomer = bank.addCustomer("Lucas");

        assertEquals(3, bank.getNumberOfCustomers());
        assertSame(firstCustomer, bank.getCustomer(0));
        assertSame(secondCustomer, bank.getCustomer(1));
        assertSame(thirdCustomer, bank.getCustomer(2));
    }

    @Test
    public void testViewBalancesForAllCustomers() {
        Bank bank = new Bank();

        bank.addCustomer("Eric");
        bank.addCustomer("Alex");

        Customer eric = bank.getCustomer(0);
        Customer alex = bank.getCustomer(1);

        eric.createAccount().deposit(50);
        eric.createAccount().deposit(75);
        alex.createAccount().deposit(100);

        assertEquals(50, eric.getAccount(0).getBalance(), 0.01);
        assertEquals(75, eric.getAccount(1).getBalance(), 0.01);
        assertEquals(100, alex.getAccount(0).getBalance(), 0.01);
    }

    @Test
    public void testGetCustomerWithInvalidIndexFails() {
        Bank bank = new Bank();
        try {
            bank.getCustomer(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // do nothing, test passes
        }
    }
}
