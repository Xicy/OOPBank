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

    public Person(String name, String lastName, Sex sex, Date birthDay) {
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    public Person(String name, String lastName) {
        this(name, lastName, Sex.Undefined, new Date(0));
    }

    protected void UpdateProfile(Object oldValue, Object newValue) {

    }

    public String getFullName() {
        return this.getName() + " " + this.getLastName();
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if(!this.name.equals(name)) {
            UpdateProfile(this.name, name);
            this.name = name;
            return true;
        }
        return false;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean setLastName(String lastName) {
        if(!this.lastName.equals(lastName)) {
            UpdateProfile(this.lastName, lastName);
            this.lastName = lastName;
            return true;
        }
        return false;
    }

    public Sex getSex() {
        return sex;
    }

    public boolean setSex(Sex sex) {
        if(!this.sex.equals(sex)) {
            UpdateProfile(this.sex, sex);
            this.sex = sex;
            return true;
        }
        return false;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public boolean setBirthDay(Date birthDay) {
        if (this.birthDay.compareTo(birthDay) != 0) {
            UpdateProfile(this.birthDay, birthDay);
            this.birthDay = birthDay;
            return true;
        }
        return false;
    }

}
