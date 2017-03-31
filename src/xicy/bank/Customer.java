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

    public Customer(Person person) {
        this(person.getName(), person.getLastName(), person.getSex(), person.getBirthDay(), 0);
    }

    public Customer(Person person, double balance) {
        this(person.getName(), person.getLastName(), person.getSex(), person.getBirthDay(), balance);
    }

    public Customer(String name, String lastName, Sex sex, Date birthDay, double balance) {
        super(name, lastName, sex, birthDay);
        this.balance = balance > 0 ? balance : 0;
    }

    public Customer(String name, String lastName) {
        this(name, lastName, Sex.Undefined, new Date(0), 0);
    }

    protected void setHistoryCollection(HistoryCollection historyCollection) {
        this.historyCollection = historyCollection;
    }

    protected void UpdateProfile(Object oldValue, Object newValue) {
        if (historyCollection != null)
            historyCollection.addEvent(Customer.this, Event.UpdateProfil, oldValue + "->" + newValue);

    }

    public long getId() {
        return this.id;
    }

    protected void setId(long id) {
        if (this.id == -1)
            this.id = id;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean drawMoney(double amount) {
        if (amount > 0 && this.balance >= amount) {
            if (historyCollection != null)
                historyCollection.addEvent(Customer.this, Event.Draw, "Before Draw Balance : " + this.balance + "₺ / Draw Amount : " + amount + "₺");
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean depositMoney(double amount) {
        if (amount > 0) {
            if (historyCollection != null)
                historyCollection.addEvent(Customer.this, Event.Deposit, "Before Deposit Balance : " + this.balance + "₺ / Deposit Amount : " + amount + "₺");
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
