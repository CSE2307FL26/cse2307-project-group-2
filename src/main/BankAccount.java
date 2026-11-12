package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {

    private double balance;
    private boolean isClosed;
    private final List<String> transactionHistory;

    public BankAccount() {
        this.balance = 0; 
        this.isClosed = false;
        this.transactionHistory = new ArrayList<>();
    }

    private void requireOpen() {
        if (isClosed) {
            throw new IllegalStateException("Account is closed"); 
        }
    }

    public void deposit(double amount) {
        requireOpen();
        if(amount > 0) {
            this.balance += amount;
            this.transactionHistory.add("Deposit: " + amount);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void closeAccount() {
        requireOpen(); 
        this.isClosed = true; 
    }

    public double getBalance() {
        requireOpen(); 
        return this.balance;
    }

    public boolean getIsClosed() {
        return this.isClosed; 
    }

    public void withdraw(double amount) {
        requireOpen();
        if(amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            this.transactionHistory.add("Withdraw: " + amount);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<String> getTransactionHistory() {
        requireOpen();
        return Collections.unmodifiableList(this.transactionHistory);
    }
    
    public void transfer(BankAccount destination, double amount) {
        requireOpen();
        if (destination.getIsClosed()) {
            throw new IllegalStateException("Account is closed");
        }
        this.withdraw(amount);
        destination.deposit(amount);
    }
}
