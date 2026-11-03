package main;

public class BankAccount {

    private double balance;
    private boolean isClosed; 

    public BankAccount() {
        this.balance = 0; 
        this.isClosed = false; 
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
      
    public void withdraw(double amount) {
        if(amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
