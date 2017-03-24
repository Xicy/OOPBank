import java.util.Calendar;
import java.util.Date;

/**
 * Created by Umut Akkaya on 18.03.2017.
 */
public class History {
    private Date dateOfOperation;
    private Customer customer;
    private Event event;
    private String info;

    public History(Customer customer, Event event, String info) {
        this.dateOfOperation = Calendar.getInstance().getTime();
        this.customer = customer;
        this.event = event;
        this.info = info;
    }

    public Date getTime()
    {
        return dateOfOperation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Event getEvent() {
        return event;
    }

    public String getInfo() {
        return info;
    }
}
