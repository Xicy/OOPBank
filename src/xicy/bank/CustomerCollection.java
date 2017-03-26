package xicy.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umut Akkaya on 17.03.2017.
 */
public class CustomerCollection {
    private Map<Long, Customer> customers;
    private long capacity;
    private long nextId;

    public CustomerCollection() {
        this(Long.MAX_VALUE);
    }

    public CustomerCollection(long capacity) {
        this.customers = new HashMap<>();
        this.capacity = capacity;
        this.nextId = -1;
    }

    public boolean addCustomer(Customer customer) {
        if (getSize() < capacity) {
            customer.setId(++nextId);
            customers.put(nextId, customer);
            return true;
        }
        return false;
    }

    public boolean removeCustomer(long id) {
        if (customers.containsKey(id)) {
            customers.remove(id);
            return true;
        }
        return false;
    }

    public Customer getCustomer(long id) {
        return customers.getOrDefault(id, null);
    }

    public Map<Long, Customer> searchCustomersWithFullName(String name) {
        Map<Long, Customer> ret = new HashMap<>();
        for (Map.Entry<Long, Customer> entry : customers.entrySet())
            if (entry.getValue().getFullName().toLowerCase().contains(name.toLowerCase()))
                ret.put(entry.getKey(), entry.getValue());
        return ret;
    }

    public Map<Long, Customer> getAllCustomers() {
        return customers;
    }

    public long getSize() {
        return customers.size();
    }

    public long getLastId() {
        return nextId;
    }
}
