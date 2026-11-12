package main;

import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 9;
	private static final int MAX_SELECTION = 9;

	private BankAccount userAccount;
    private Customer customer;
    private Scanner keyboardInput;

    public MainMenu() {
        this.customer = new Customer("Customer");
        this.userAccount = customer.createAccount();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        
        System.out.println("1. Make a deposit");
        System.out.println("2. View transaction history");
        System.out.println("3. Make a withdrawal");
        System.out.println("4. Check account balances");
        System.out.println("5. Open an additional account");
        System.out.println("6. Switch accounts");
        System.out.println("7. Close this account");
        System.out.println("8. Transfer to another account");
        System.out.println("9. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            if (keyboardInput.hasNextInt()) {
                selection = keyboardInput.nextInt();
            } else {
                keyboardInput.next();
            }
        }
        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                viewTransactionHistory();
                break;
            case 3:
                performWithdraw();
                break;
            case 4:
                viewBalances();
                break;
            case 5:
                openAccount();
                break;
            case 6:
                switchAccount();
                break;
            case 7:
                closeAccount();
                break;
            case 8:
                transferMoney();
                break;
        }
    }

    public double readAmount(String prompt) {
        double amount = -1;
        while(amount <= 0) {
            System.out.print(prompt);
            if (keyboardInput.hasNextDouble()) {
                amount = keyboardInput.nextDouble();
            } else {
                keyboardInput.next();
            }
        }
        return amount;
    }

    public void performDeposit() {
        userAccount.deposit(readAmount("How much would you like to deposit: "));
    }

    public void performWithdraw() {
        userAccount.withdraw(readAmount("How much would you like to withdraw: "));
    }

    public void viewBalances() {
        for (int i = 0; i < customer.getNumberOfAccounts(); i++) {
            BankAccount account = customer.getAccount(i);
            if (account.getIsClosed()) {
                System.out.println("Account " + (i + 1) + ": closed");
            } else {
                System.out.println("Account " + (i + 1) + ": $" + account.getBalance());
            }
        }
    }

    public void openAccount() {
        userAccount = customer.createAccount();
        System.out.println("Opened account " + customer.getNumberOfAccounts() + ". You are now using it.");
    }

    public void switchAccount() {
        viewBalances();
        int accountNumber = getUserSelection(customer.getNumberOfAccounts());
        userAccount = customer.getAccount(accountNumber - 1);
    }

    public void closeAccount() {
        userAccount.closeAccount();
        System.out.println("Account closed.");
    }

    public void transferMoney() {
        viewBalances();
        int accountNumber = getUserSelection(customer.getNumberOfAccounts());
        userAccount.transfer(customer.getAccount(accountNumber - 1), readAmount("How much would you like to transfer: "));
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction history:");
        for (String entry : userAccount.getTransactionHistory()) {
            System.out.println(entry);
        }
    }

    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            try {
                processInput(selection);
            } catch (IllegalStateException e) {
                System.out.println("This account is closed.");
            } catch (IllegalArgumentException e) {
                System.out.println("You don't have enough money in this account.");
            }
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }

}
