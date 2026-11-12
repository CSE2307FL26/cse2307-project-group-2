package test;

import main.BankAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue; 
import static org.junit.jupiter.api.Assertions.assertFalse; 
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

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
    }
      
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

    @Test 
    public void testWithdrawFromClosedAccountFails() {
        BankAccount testAccount = new BankAccount(); 
        testAccount.closeAccount(); 
        try {
            testAccount.withdraw(150);
            fail();
        } catch (IllegalStateException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testNewAccountHasEmptyTransactionHistory() {
        BankAccount testAccount = new BankAccount();
        assertTrue(testAccount.getTransactionHistory().isEmpty());
    }

    @Test
    public void testTransactionHistoryRecordsDepositAndWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(40);

        List<String> history = testAccount.getTransactionHistory();
        assertEquals(2, history.size());
        assertEquals("Deposit: 100.0", history.get(0));
        assertEquals("Withdraw: 40.0", history.get(1));
    }

    @Test
    public void testFailedWithdrawDoesNotRecordTransaction() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        try {
            testAccount.withdraw(100);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
        assertEquals(1, testAccount.getTransactionHistory().size());
        assertEquals("Deposit: 50.0", testAccount.getTransactionHistory().get(0));
    }

    @Test
    public void testViewTransactionHistoryOfClosedAccountFails() {
        BankAccount testAccount = new BankAccount();
        testAccount.closeAccount();
        try {
            testAccount.getTransactionHistory();
            fail();
        } catch (IllegalStateException e) {
            // do nothing, test passes
        }
    }

    @Test
    public void testTransfer() {
        BankAccount fromAccount = new BankAccount();
        BankAccount toAccount = new BankAccount();
        fromAccount.deposit(100);
        toAccount.deposit(20);

        fromAccount.transfer(toAccount, 40);

        assertEquals(60, fromAccount.getBalance(), 0.01);
        assertEquals(60, toAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testTransferWithInsufficientFundsFails() {
        BankAccount fromAccount = new BankAccount();
        BankAccount toAccount = new BankAccount();
        fromAccount.deposit(50);
        try {
            fromAccount.transfer(toAccount, 75);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
        assertEquals(50, fromAccount.getBalance(), 0.01);
        assertEquals(0, toAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferFromClosedAccountFails() {
        BankAccount fromAccount = new BankAccount();
        BankAccount toAccount = new BankAccount();
        fromAccount.deposit(100);
        fromAccount.closeAccount();
        try {
            fromAccount.transfer(toAccount, 40);
            fail();
        } catch (IllegalStateException e) {
            //do nothing, test passes
        }
        assertEquals(0, toAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferToClosedAccountFails() {
        BankAccount fromAccount = new BankAccount();
        BankAccount toAccount = new BankAccount();
        fromAccount.deposit(100);
        toAccount.closeAccount();
        try {
            fromAccount.transfer(toAccount, 40);
            fail();
        } catch (IllegalStateException e) {
            //do nothing, test passes
        }
        assertEquals(100, fromAccount.getBalance(), 0.01);
    }
}
