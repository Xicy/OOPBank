package xicy.bank;

import java.util.Date;

/**
 * Created by Umut Akkaya on 20.03.2017.
 */
public class Person {
    private String name;
    private String lastName;
    private Sex sex;
    private Date birthDay;

    /**
     * Instantiates a new Person.
     *
     * @param name     the name
     * @param lastName the last name
     * @param sex      the sex
     * @param birthDay the birth day
     */
    public Person(String name, String lastName, Sex sex, Date birthDay) {
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    /**
     * Instantiates a new Person.
     *
     * @param name     the name
     * @param lastName the last name
     */
    public Person(String name, String lastName) {
        this(name, lastName, Sex.Undefined, new Date(0));
    }

    /**
     * Update profile.
     *
     * @param oldValue the old value
     * @param newValue the new value
     */
    protected void UpdateProfile(Object oldValue, Object newValue) {

    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return this.getName() + " " + this.getLastName();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public boolean setName(String name) {
        if (!this.name.equals(name)) {
            UpdateProfile(this.name, name);
            this.name = name;
            return true;
        }
        return false;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     * @return the last name
     */
    public boolean setLastName(String lastName) {
        if (!this.lastName.equals(lastName)) {
            UpdateProfile(this.lastName, lastName);
            this.lastName = lastName;
            return true;
        }
        return false;
    }

    /**
     * Gets sex.
     *
     * @return the sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     * @return the sex
     */
    public boolean setSex(Sex sex) {
        if (!this.sex.equals(sex)) {
            UpdateProfile(this.sex, sex);
            this.sex = sex;
            return true;
        }
        return false;
    }

    /**
     * Gets birth day.
     *
     * @return the birth day
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * Sets birth day.
     *
     * @param birthDay the birth day
     * @return the birth day
     */
    public boolean setBirthDay(Date birthDay) {
        if (this.birthDay.compareTo(birthDay) != 0) {
            UpdateProfile(this.birthDay, birthDay);
            this.birthDay = birthDay;
            return true;
        }
        return false;
    }

}
