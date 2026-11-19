package main; 

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers; 
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Bank() {
        this.customers = new ArrayList<>(); 
    }

    public Customer addCustomer(String name) {
        Customer customer = new Customer(name); 
        customers.add(customer); 
        return customer; 
    }

    public int getNumberOfCustomers() {
        return customers.size(); 
    }

    public Customer getCustomer(int index) {
        if (index < 0 || index >= customers.size()) {
            throw new IndexOutOfBoundsException("Invalid customer index"); 
        }
        return customers.get(index); 
    }

    public void save(String fileName) throws IOException {
        Files.writeString(Path.of(fileName), GSON.toJson(this));
    }

    public static Bank load(String fileName) {
        try {
            return GSON.fromJson(Files.readString(Path.of(fileName)), Bank.class);
        } catch (IOException e) {
            return new Bank();
        }
    }
}