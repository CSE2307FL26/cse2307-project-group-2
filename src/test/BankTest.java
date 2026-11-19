package test;

import main.Bank;
import main.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

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

    @Test
    public void testSaveAndLoadKeepsAccounts(@TempDir Path folder) throws IOException {
        String fileName = folder.resolve("bank.json").toString();
        Bank bank = new Bank();
        Customer eric = bank.addCustomer("Eric");
        eric.createAccount().deposit(50);
        eric.createAccount().closeAccount();
        bank.save(fileName);

        Customer loaded = Bank.load(fileName).getCustomer(0);

        assertEquals("Eric", loaded.getName());
        assertEquals(50, loaded.getAccount(0).getBalance(), 0.01);
        assertEquals("Deposit: 50.0", loaded.getAccount(0).getTransactionHistory().get(0));
        assertTrue(loaded.getAccount(1).getIsClosed());
    }

    @Test
    public void testLoadWithNoSaveFileStartsEmpty(@TempDir Path folder) {
        Bank bank = Bank.load(folder.resolve("missing.json").toString());
        assertEquals(0, bank.getNumberOfCustomers());
    }
}
