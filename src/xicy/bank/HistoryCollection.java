package xicy.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umut Akkaya on 18.03.2017.
 */
public class HistoryCollection {
    private final Map<Long, History> histories;
    private long nextHistory;

    /**
     * Instantiates a new History collection.
     */
    public HistoryCollection() {
        histories = new HashMap<>();
        nextHistory = 0;
    }

    /**
     * Contains key boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean containsKey(long id) {
        return histories.containsKey(id);
    }

    /**
     * Add event.
     *
     * @param customer the customer
     * @param event    the event
     * @param info     the info
     */
    public void add(Customer customer, Event event, String info) {
        histories.put(nextHistory++, new History(customer, event,info));
    }

    /**
     * Gets history.
     *
     * @param id the id
     * @return the history
     */
    public History get(long id) {
        return histories.get(id);
    }

    /**
     * Gets all history.
     *
     * @return the all history
     */
    public Map<Long, History> getAll() {
        return histories;
    }

    /**
     * Gets history of user.
     *
     * @param customerId the customer ıd
     * @return the history of user
     */
    public Map<Long, History> getHistoryOfUser(long customerId) {
        Map<Long, History> ret = new HashMap<>();
        for (Map.Entry<Long, History> entry : histories.entrySet())
            if (entry.getValue().getCustomer().getId() == customerId)
                ret.put(entry.getKey(), entry.getValue());
        return ret;
    }
}
