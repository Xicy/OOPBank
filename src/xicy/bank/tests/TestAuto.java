package xicy.bank.tests;

import xicy.bank.*;

import java.util.*;

/**
 * Created by Umut Akkaya on 17.03.2017.
 */
public class TestAuto {

    private static final Person personTest = new Person("Test Auto", "Test Auto", Sex.Undefined, getDate(1, 1, 2000));
    private static final Person personUmutAkkaya = new Person("Umut", "Akkaya", Sex.Male, getDate(5, 12, 1996));
    private static final Person personUmutKaya = new Person("Umut", "Kaya", Sex.Male, getDate(17, 12, 1989));
    private static final Person personDevrimNazAkdas = new Person("Devrim", "Naz Akdas", Sex.Female, getDate(23, 3, 1997));
    private static final Person personEmreSatir = new Person("Emre", "Satir", Sex.Male, getDate(1, 1, 1990));

    private static final Bank testBank = new Bank("Test Auto Bank");
    private static final Bank limitedBank = new Bank("Litmited Bank", 3);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        testBank(testBank);
        testPerson(personTest);

        Customer customerTestUpdateProfile = testBank.getCustomer(1);
        customerTestUpdateProfile.setName("Test Auto Name");
        customerTestUpdateProfile.setLastName("Test Auto Lastname");
        customerTestUpdateProfile.setSex(Sex.Male);
        customerTestUpdateProfile.setBirthDay(getDate(10, 10, 2010));

        testLimitedBank(limitedBank);

        Random r = new Random();
        for (int i = 1; i <= 20; i++) {
            Customer customerRandom = limitedBank.getCustomer(r.nextLong() % limitedBank.getCustomerCount() + 1);
            if (customerRandom == null)
                continue;
            customerRandom.depositMoney(i);
            customerRandom.drawMoney(i * r.nextFloat());
        }


        System.out.println();
        System.out.println("---Test Auto Bank History---");
        printHistory(testBank.getAllHistory());
        System.out.println();
        System.out.println("---Test Auto Bank History Of User---");
        printHistory(testBank.getHistoryOfUser(0));
        System.out.println();
        System.out.println("---Limited Bank History---");
        printHistory(limitedBank.getAllHistory());
    }

    private static void testPerson(Person cus) {
        System.out.println();
        System.out.println("---Customer Test Auto Starting---");
        System.out.println();
        System.out.println("1.Get Customer Name : " + (cus.getName().equals("Test Auto")));
        System.out.println("2.Get Customer LastName : " + (cus.getLastName().equals("Test Auto")));
        System.out.println("3.Get Customer FullName : " + (cus.getFullName().equals("Test Auto Test Auto")));
        System.out.println("4.Get Customer Sex : " + (cus.getSex().equals(Sex.Undefined)));
        System.out.println("5.Get Customer BirthDay : " + isTimeEqual(cus.getBirthDay(), getDate(1, 1, 2000)));
        System.out.println();
        System.out.println("6.Set Customer Name : " + (cus.setName("Test Auto 1")));
        System.out.println("7.Set Customer LastName : " + (cus.setLastName("Test Auto 2")));
        System.out.println("8.Get Customer Sex : " + (cus.setSex(Sex.Female)));
        System.out.println("9.Get Customer BirthDay : " + (cus.setBirthDay(getDate(12, 12, 2012))));
        System.out.println();
        System.out.println("10.Get Customer Name : " + (cus.getName().equals("Test Auto 1")));
        System.out.println("11.Get Customer LastName : " + (cus.getLastName().equals("Test Auto 2")));
        System.out.println("12.Get Customer FullName : " + (cus.getFullName().equals("Test Auto 1 Test Auto 2")));
        System.out.println("13.Get Customer Sex : " + (cus.getSex().equals(Sex.Female)));
        System.out.println("14.Get Customer BirthDay : " + isTimeEqual(cus.getBirthDay(), getDate(12, 12, 2012)));
        System.out.println();
        System.out.println("---Customer Test Auto Ended---");
    }

    private static void testCustomer(Customer cus) {
        System.out.println();
        double cusMoney = cus.getBalance();
        System.out.println("\tDraw Customer Money : " + (cus.drawMoney(1)));
        System.out.println("\tGet Customer Balance : " + (cus.getBalance() == (cusMoney = cusMoney - 1)));
        System.out.println("\tDeposit Customer Money : " + (cus.depositMoney(2)));
        System.out.println("\tGet Customer Balance : " + (cus.getBalance() == cusMoney + 2));
    }

    private static void testBank(Bank bank) {
        System.out.println();
        System.out.println("---Bank Test Auto Starting---");
        System.out.println();
        System.out.println("1.Get Bank Name : " + (bank.getName().equals("Test Auto Bank")));
        System.out.println("2.Set Bank Name : " + (bank.setName("Test Auto 2 Bank")));
        System.out.println("3.Get Bank Name : " + (bank.getName().equals("Test Auto 2 Bank")));
        System.out.println();
        System.out.println("4.Get Customer Count : " + (bank.getCustomerCount() == 0));
        System.out.println("5.Get Last Customer ID : " + (bank.getLastCustomerId() == -1));
        System.out.println("6.Get All Customers : " + bank.getAllCustomers());
        System.out.println();
        System.out.println("7.Add Customer : " + bank.addCustomer(personUmutAkkaya, 10));
        System.out.println("8.Add Customer : " + bank.addCustomer(personUmutKaya, 20));
        System.out.println("9.Add Customer : " + bank.addCustomer(personDevrimNazAkdas, 30));
        System.out.println("10.Add Customer : " + bank.addCustomer(personEmreSatir, 40));
        System.out.println();
        System.out.println("11.Get All Customers : ");
        printAllCustomer(bank.getAllCustomers());
        System.out.println("12.Search Customers : ");
        printAllCustomer(bank.searchCustomersWithFullName("Ak"));
        System.out.println();
        System.out.println("13.Get Customer Count : " + (bank.getCustomerCount() == 4));
        System.out.println("14.Get Last Customer ID : " + (bank.getLastCustomerId() == 3));
        System.out.println();
        System.out.println("15.Get Customer With ID : " + bank.getCustomer(2));
        System.out.println("16.Remove Customer With ID: " + bank.removeCustomer(2));
        System.out.println("17.Get Removed Customer : " + (bank.getCustomer(2) == null));
        System.out.println("18.Add Removed Customer : " + bank.addCustomer(personUmutKaya, 50));
        System.out.println();
        System.out.println("19.Get All Customers: ");
        printAllCustomer(bank.getAllCustomers());

        testCustomer(bank.getCustomer(1));

        System.out.println();
        System.out.println("---Bank Test Auto Ended---");
    }

    private static void testLimitedBank(Bank bank) {
        System.out.println();
        System.out.println("---Limited Bank Test Auto Starting---");
        System.out.println();
        System.out.println("1.Get Customer Count : " + (bank.getCustomerCount() == 0));
        System.out.println("2.Get Last Customer ID : " + (bank.getLastCustomerId() == -1));
        System.out.println("3.Get All Customers : " + bank.getAllCustomers());
        System.out.println();
        System.out.println("4.Add Customer : " + bank.addCustomer(personUmutAkkaya, 60));
        System.out.println("5.Add Customer : " + bank.addCustomer(personUmutKaya, 70));
        System.out.println("6.Add Customer : " + bank.addCustomer(personDevrimNazAkdas, 80));
        System.out.println("7.Add Customer : " + (!bank.addCustomer(personEmreSatir, 90)));
        System.out.println();
        System.out.println("8.Get All Customers : ");
        printAllCustomer(bank.getAllCustomers());
        System.out.println("9.Search Customers : ");
        printAllCustomer(bank.searchCustomersWithFullName("Ak"));
        System.out.println();
        System.out.println("10.Get Customer Count : " + (bank.getCustomerCount() == 3));
        System.out.println("11.Get Last Customer ID : " + (bank.getLastCustomerId() == 2));
        System.out.println();
        System.out.println("12.Get Customer With ID : " + (bank.getCustomer(0) == bank.searchCustomersWithFullName("Umut Akkaya").entrySet().iterator().next().getValue()));
        System.out.println("13.Remove Customer With ID : " + bank.removeCustomer(1));
        System.out.println("14.Get Removed Customer : " + (bank.getCustomer(1) == null));
        System.out.println("15.Add Removed Customer : " + bank.addCustomer(personUmutAkkaya, 100));
        System.out.println();
        System.out.println("16.Get All Customers : ");
        printAllCustomer(bank.getAllCustomers());

        testCustomer(bank.getCustomer(2));

        System.out.println();
        System.out.println("---Limited Bank TestAuto Ended---");
    }

    private static void printAllCustomer(Map<Long, Customer> customers) {
        for (Map.Entry<Long, Customer> cus : customers.entrySet()) {
            System.out.println("\t" + cus.getKey() + ". " + cus.getValue());
        }
    }

    private static void printHistory(Map<Long, History> histories) {
        for (Map.Entry<Long, History> his : histories.entrySet()) {
            System.out.println("\t" + his.getKey() + ". [" + his.getValue().getTime() + "] [" + his.getValue().getEvent() + "] " + his.getValue().getCustomer() + " " + his.getValue().getInfo());
        }
    }

    private static Date getDate(int date, int month, int year) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(year, month - 1, date, 0, 0);
        return cal.getTime();
    }

    private static boolean isTimeEqual(Date first, Date second) {
        return first.toString().equals(second.toString());
    }

}
