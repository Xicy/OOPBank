package xicy.bank;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Umut Akkaya on 17.03.2017.
 */
public class Customer extends Person {
    private long id = -1;
    private double balance;
    private HistoryCollection historyCollection;

    /**
     * Instantiates a new Customer.
     *
     * @param person the person
     */
    public Customer(Person person) {
        this(person.getName(), person.getLastName(), person.getSex(), person.getBirthDay(), 0);
    }

    /**
     * Instantiates a new Customer.
     *
     * @param person  Customer information
     * @param balance the starting balance of customer
     */
    public Customer(Person person, double balance) {
        this(person.getName(), person.getLastName(), person.getSex(), person.getBirthDay(), balance);
    }

    /**
     * Instantiates a new Customer.
     *
     * @param name     the name of customer
     * @param lastName the last name of customer
     * @param sex      the sex of customer
     * @param birthDay the birth day of customer
     * @param balance  the starting balance of customer
     */
    public Customer(String name, String lastName, Sex sex, Date birthDay, double balance) {
        super(name, lastName, sex, birthDay);
        this.balance = balance > 0 ? balance : 0;
    }

    /**
     * Instantiates a new Customer.
     *
     * @param name     the name of customer
     * @param lastName the last name of customer
     */
    public Customer(String name, String lastName) {
        this(name, lastName, Sex.Undefined, new Date(0), 0);
    }

    /**
     * Sets history collection.
     * This starts only once time
     *
     * @param historyCollection the history collection
     */
    protected void setHistoryCollection(HistoryCollection historyCollection) {
        if (this.historyCollection == null)
            this.historyCollection = historyCollection;
    }

    protected void UpdateProfile(Object oldValue, Object newValue) {
        if (historyCollection != null)
            historyCollection.add(Customer.this, Event.UpdateProfil, oldValue + "->" + newValue);

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     * This starts only once time
     *
     * @param id the id
     */
    protected void setId(long id) {
        if (this.id == -1)
            this.id = id;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Draw money.
     *
     * @param amount the amount of money
     * @return Draw status
     */
    public boolean drawMoney(double amount) {
        if (amount > 0 && this.balance >= amount) {
            if (historyCollection != null)
                historyCollection.add(Customer.this, Event.Draw, "Before Draw Balance : " + this.balance + "₺ / Draw Amount : " + amount + "₺");
            this.balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Deposit money.
     *
     * @param amount the amount of money
     * @return Deposit status
     */
    public boolean depositMoney(double amount) {
        if (amount > 0) {
            if (historyCollection != null)
                historyCollection.add(Customer.this, Event.Deposit, "Before Deposit Balance : " + this.balance + "₺ / Deposit Amount : " + amount + "₺");
            this.balance += amount;
            return true;
        }
        return false;
    }

    public String toString() {
        return this.getFullName() + "\t" + this.getSex() + "\t" + new SimpleDateFormat("dd.MM.yyyy").format(this.getBirthDay()) + "\t" + this.balance + "₺";
    }

    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && id == ((Customer) o).id;
    }

}
