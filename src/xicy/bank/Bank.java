package xicy.bank;

import java.util.Map;

/**
 * Created by Umut Akkaya on 17.03.2017.
 */
public class Bank {
    private final CustomerCollection customerCollection;
    private final HistoryCollection historyCollection;
    private String name;

    /**
     * Instantiates a new Bank.
     *
     * @param name Name of Bank
     */
    public Bank(String name) {
        this(name, Long.MAX_VALUE);
    }

    /**
     * Instantiates a new Bank.
     *
     * @param name     Name of Bank
     * @param capacity Member capacity of bank
     */
    public Bank(String name, long capacity) {
        this.name = name;
        this.customerCollection = new CustomerCollection(capacity);
        this.historyCollection = new HistoryCollection();
    }

    /**
     * Add customer.
     *
     * @param person  Customer information
     * @param balance Customer starting balance
     * @return added status
     */
    public boolean addCustomer(Person person, double balance) {
        Customer customer = new Customer(person, balance);
        boolean ret = customerCollection.add(customer);
        if (ret) {
            customer.setHistoryCollection(historyCollection);
            historyCollection.add(customer, Event.NewCustomer, "");
        }
        return ret;
    }

    /**
     * Remove customer.
     *
     * @param id Customer id
     * @return Removed status
     */
    public boolean removeCustomer(long id) {
        Customer get = getCustomer(id);
        boolean ret = customerCollection.remove(id);
        if (ret) {
            historyCollection.add(get, Event.RemoveCustomer, "");
        }
        return ret;
    }

    /**
     * Gets customer.
     *
     * @param id Customer id
     * @return Customer of id
     */
    public Customer getCustomer(long id) {
        return customerCollection.get(id);
    }


    /**
     * Gets all customers.
     *
     * @return All customer of bank
     */
    public Map<Long, Customer> getAllCustomers() {
        return customerCollection.getAll();
    }

    /**
     * Search customers with full name map.
     *
     * @param name Full name of customer not case sensetive
     * @return Customer list which are contains name
     */
    public Map<Long, Customer> searchCustomersWithFullName(String name) {
        return customerCollection.searchWithFullName(name);
    }

    /**
     * Gets customer count.
     *
     * @return Count of customer
     */
    public long getCustomerCount() {
        return customerCollection.getSize();
    }

    /**
     * Gets last customer id.
     *
     * @return Last customer id
     */
    public long getLastCustomerId() {
        return customerCollection.getLastId();
    }

    /**
     * Gets history.
     *
     * @param id Id of history
     * @return History history
     */
    public History getHistory(long id) {
        if (historyCollection.containsKey(id))
            return historyCollection.get(id);
        else
            return null;
    }

    /**
     * Gets all history.
     *
     * @return All history of bank
     */
    public Map<Long, History> getAllHistory() {
        return historyCollection.getAll();
    }

    /**
     * Gets history of user.
     *
     * @param customerId Customer id
     * @return History of customer
     */
    public Map<Long, History> getHistoryOfUser(long customerId) {
        return historyCollection.getHistoryOfUser(customerId);
    }

    /**
     * Gets name.
     *
     * @return Get bank name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name Set bank name
     * @return Setted status
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }
}
