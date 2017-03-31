package xicy.bank;

import java.util.Map;

/**
 * Created by Umut Akkaya on 17.03.2017.
 */
public class Bank {
    private String name;
    private CustomerCollection customerCollection;
    private HistoryCollection historyCollection;

    public Bank(String name) {
        this.name = name;
        this.customerCollection = new CustomerCollection();
        this.historyCollection = new HistoryCollection();
    }

    public Bank(String name, long capacity) {
        this.name = name;
        this.customerCollection = new CustomerCollection(capacity);
        this.historyCollection = new HistoryCollection();
    }

    public boolean addCustomer(Person person, double balance) {
        Customer customer = new Customer(person, balance);
        boolean ret = customerCollection.addCustomer(customer);
        if (ret) {
            customer.setHistoryCollection(historyCollection);
            historyCollection.addEvent(customer, Event.NewCustomer, "");
        }
        return ret;
    }

    public boolean removeCustomer(long id) {
        Customer get = getCustomer(id);
        boolean ret = customerCollection.removeCustomer(id);
        if (ret) {
            historyCollection.addEvent(get, Event.RemoveCustomer, "");
        }
        return ret;
    }

    public Customer getCustomer(long id) {
        return customerCollection.getCustomer(id);
    }

    public Map<Long, Customer> getAllCustomers() {
        return customerCollection.getAllCustomers();
    }

    public Map<Long, Customer> searchCustomersWithFullName(String name) {
        return customerCollection.searchCustomersWithFullName(name);
    }

    public long getCustomerCount() {
        return customerCollection.getSize();
    }

    public long getLastCustomerId() {
        return customerCollection.getLastId();
    }

    public History getHistory(long id) {
        if (historyCollection.containsKey(id))
            return historyCollection.getHistory(id);
        else
            return null;
    }

    public Map<Long, History> getAllHistory() {
        return historyCollection.getAllHistory();
    }

    public Map<Long, History> getHistoryOfUser(long customerId) {
        return historyCollection.getHistoryOfUser(customerId);
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }
}
