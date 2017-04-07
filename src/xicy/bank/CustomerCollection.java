package xicy.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umut Akkaya on 17.03.2017.
 */
public class CustomerCollection {
    private final Map<Long, Customer> customers;
    private final long capacity;
    private long nextId;

    /**
     * Instantiates a new Customer collection.
     */
    public CustomerCollection() {
        this(Long.MAX_VALUE);
    }

    /**
     * Instantiates a new Customer collection.
     *
     * @param capacity the capacity
     */
    public CustomerCollection(long capacity) {
        this.customers = new HashMap<>();
        this.capacity = capacity;
        this.nextId = -1;
    }

    /**
     * Add customer boolean.
     *
     * @param customer the customer
     * @return the boolean
     */
    public boolean add(Customer customer) {
        if (getSize() < capacity) {
            customer.setId(++nextId);
            customers.put(nextId, customer);
            return true;
        }
        return false;
    }

    /**
     * Remove customer boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean remove(long id) {
        if (customers.containsKey(id)) {
            customers.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Gets customer.
     *
     * @param id the id
     * @return the customer
     */
    public Customer get(long id) {
        return customers.getOrDefault(id, null);
    }

    /**
     * Search customers with full name map.
     *
     * @param name the name
     * @return the map
     */
    public Map<Long, Customer> searchWithFullName(String name) {
        Map<Long, Customer> ret = new HashMap<>();
        for (Map.Entry<Long, Customer> entry : customers.entrySet())
            if (entry.getValue().getFullName().toLowerCase().contains(name.toLowerCase()))
                ret.put(entry.getKey(), entry.getValue());
        return ret;
    }

    /**
     * Gets all customers.
     *
     * @return the all customers
     */
    public Map<Long, Customer> getAll() {
        return customers;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public long getSize() {
        return customers.size();
    }

    /**
     * Gets last ıd.
     *
     * @return the last ıd
     */
    public long getLastId() {
        return nextId;
    }
}
