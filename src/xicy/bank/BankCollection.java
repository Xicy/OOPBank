package xicy.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umut Akkaya on 17.03.2017.
 */
public class BankCollection {
    private final Map<Long, Bank> banks;
    private final long capacity;
    private long nextId;


    /**
     * Instantiates a new Bank collection.
     */
    public BankCollection() {
        this(Long.MAX_VALUE);
    }

    /**
     * Instantiates a new Bank collection.
     *
     * @param capacity Maximum bank capacity
     */
    public BankCollection(long capacity) {
        this.banks = new HashMap<>();
        this.capacity = capacity;
        this.nextId = 0;
    }

    /**
     * Add bank.
     *
     * @param bank Bank of adding
     * @return Added Status
     */
    public boolean add(Bank bank) {
        if (getSize() < capacity) {
            banks.put(nextId++, bank);
            return true;
        }
        return false;
    }

    /**
     * Remove bank.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean remove(long id) {
        if (banks.containsKey(id)) {
            banks.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Gets bank.
     *
     * @param id the id
     * @return the bank
     */
    public Bank get(long id) {
        return banks.getOrDefault(id, null);
    }

    /**
     * Search banks with name map.
     *
     * @param name the name
     * @return the map
     */
    public Map<Long, Bank> searchBanksWithName(String name) {
        Map<Long, Bank> ret = new HashMap<>();
        for (Map.Entry<Long, Bank> entry : banks.entrySet())
            if (entry.getValue().getName().toLowerCase().contains(name.toLowerCase()))
                ret.put(entry.getKey(), entry.getValue());
        return ret;
    }

    /**
     * Gets all banks.
     *
     * @return the all banks
     */
    public Map<Long, Bank> getAll() {
        return banks;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public long getSize() {
        return banks.size();
    }

    /**
     * Gets last id.
     *
     * @return the last id
     */
    public long getLastId() {
        return nextId;
    }
}
