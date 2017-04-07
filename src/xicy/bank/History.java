package xicy.bank;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Umut Akkaya on 18.03.2017.
 */
public class History {
    private final Date dateOfOperation;
    private final Customer customer;
    private final Event event;
    private final String info;

    /**
     * Instantiates a new History.
     *
     * @param customer the customer
     * @param event    the event
     * @param info     the info
     */
    public History(Customer customer, Event event, String info) {
        this.dateOfOperation = Calendar.getInstance().getTime();
        this.customer = customer;
        this.event = event;
        this.info = info;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Date getTime() {
        return dateOfOperation;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets event.
     *
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Gets ınfo.
     *
     * @return the ınfo
     */
    public String getInfo() {
        return info;
    }
}
