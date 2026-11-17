package main; 

import java.util.ArrayList; 
import java.util.List; 

public class Bank {
    private List<Customer> customers; 

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
}