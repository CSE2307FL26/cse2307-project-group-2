package test;

import main.BankAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testNewAccountHasZeroBalance() {
        BankAccount account = new BankAccount();
        assertEquals(0, account.getBalance(), 0.01);
    }

    @Test
    public void testCheckBalanceForAnyAccountActivelyPossessed() {
        BankAccount testAccount1 = new BankAccount(); 
        BankAccount testAccount2 = new BankAccount(); 

        testAccount1.deposit(50);
        testAccount2.deposit(120);

        assertEquals(50, testAccount1.getBalance(), 0.01); 
        assertEquals(120, testAccount2.getBalance(), 0.01); 
      
    @Test
    public void testWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        try {
            testAccount.withdraw(150);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }
}
