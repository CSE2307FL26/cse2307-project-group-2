package test;

import main.BankAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue; 
import static org.junit.jupiter.api.Assertions.assertFalse; 
import static org.junit.jupiter.api.Assertions.fail; 

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test 
    public void testClosingAccount() {
        BankAccount testAccount = new BankAccount(); 
        testAccount.closeAccount(); 
        assertTrue(testAccount.getIsClosed()); 
    }

    @Test 
    public void testCheckBalanceOfClosedAccountFails() {
        BankAccount testAccount = new BankAccount(); 
        testAccount.closeAccount(); 
        try {
            testAccount.getBalance(); 
            fail(); 
        } catch (IllegalStateException e) {
            // do nothing, test passes
        }
    }

    @Test 
    public void testDepositIntoClosedAccountFails() {
        BankAccount testAccount = new BankAccount(); 
        testAccount.closeAccount(); 
        try {
            testAccount.deposit(50); 
            fail(); 
        } catch (IllegalStateException e) {
            // do nothing, test passes
        }
    }

    @Test 
    public void testClosingAlreadyClosedAccountFails() {
        BankAccount testAccount = new BankAccount(); 
        testAccount.closeAccount(); 
        try {
            testAccount.closeAccount(); 
            fail(); 
        } catch (IllegalStateException e) {
            // do nothing, test passes
        }
    }

    @Test 
    public void testClosingOneAccountDoesNotAffectAnother() {
        BankAccount testAccount1 = new BankAccount(); 
        BankAccount testAccount2 = new BankAccount(); 

        testAccount1.deposit(50);
        testAccount2.closeAccount();
        assertEquals(50, testAccount1.getBalance(), 0.01);
        assertFalse(testAccount1.getIsClosed());
        assertTrue(testAccount2.getIsClosed());
    }
}
