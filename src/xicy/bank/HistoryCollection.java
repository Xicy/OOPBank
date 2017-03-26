package xicy.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umut Akkaya on 18.03.2017.
 */
public class HistoryCollection {
    private Map<Long, History> histories;
    private long nextHistory;

    public HistoryCollection() {
        histories = new HashMap<>();
        nextHistory = 0;
    }

    public void addEvent(Customer customer, Event event, String info) {
        histories.put(nextHistory++, new History(customer, event,info));
    }

    public History getHistory(long id) {
        return histories.get(id);
    }

    public Map<Long, History> getAllHistory() {
        return histories;
    }

    public Map<Long, History> getHistoryOfUser(long customerId) {
        Map<Long, History> ret = new HashMap<>();
        for (Map.Entry<Long, History> entry : histories.entrySet())
            if (entry.getValue().getCustomer().getId() == customerId)
                ret.put(entry.getKey(), entry.getValue());
        return ret;
    }
}
