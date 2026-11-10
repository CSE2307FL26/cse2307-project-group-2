package main;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<BankAccount> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public BankAccount createAccount() {
        BankAccount account = new BankAccount();
        accounts.add(account);
        return account;
    }

    public List<BankAccount> listAccounts() {
        return accounts;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }   

    public BankAccount getAccount(int index) {
        if (index < 0 || index >= accounts.size()) {
            throw new IndexOutOfBoundsException("Invalid account index");
        }
        return accounts.get(index);
    }

}