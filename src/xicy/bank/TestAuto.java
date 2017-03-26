package xicy.bank;

import java.util.*;

/**
 * Created by Umut Akkaya on 17.03.2017.
 */
public class TestAuto {

    private static Person personTest = new Person("xicy.bank.TestAuto", "xicy.bank.TestAuto", Sex.Undefined, getDate(1, 1, 2000));
    private static Person personUmutAkkaya = new Person("Umut", "Akkaya", Sex.Male, getDate(5, 12, 1996));
    private static Person personUmutKaya = new Person("Umut", "Kaya", Sex.Male, getDate(17, 12, 1989));
    private static Person personDevrimNazAkdas = new Person("Devrim", "Naz Akdaş", Sex.Female, getDate(23, 3, 1997));
    private static Person personEmreSatir = new Person("Emre", "Şatır", Sex.Male, getDate(1, 1, 1990));

    private static Bank testBank = new Bank("xicy.bank.TestAuto xicy.bank.Bank");
    private static Bank limitedBank = new Bank("Litmited xicy.bank.Bank", 3);

    public static void main(String[] args) {
        testBank(testBank);
        testPerson(personTest);

        Customer customerTestUpdateProfile = testBank.getCustomer(1);
        customerTestUpdateProfile.setName("xicy.bank.TestAuto Name");
        customerTestUpdateProfile.setLastName("xicy.bank.TestAuto Lastname");
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
        System.out.println("---xicy.bank.TestAuto xicy.bank.Bank xicy.bank.History---");
        printHistory(testBank.getAllHistory());
        System.out.println();
        System.out.println("---xicy.bank.TestAuto xicy.bank.Bank xicy.bank.History Of User---");
        printHistory(testBank.getHistoryOfUser(0));
        System.out.println();
        System.out.println("---Limited xicy.bank.Bank xicy.bank.History---");
        printHistory(limitedBank.getAllHistory());
    }

    public static void testPerson(Person cus) {
        System.out.println();
        System.out.println("---xicy.bank.Customer xicy.bank.TestAuto Starting---");
        System.out.println();
        System.out.println("1.Get xicy.bank.Customer Name : " + (cus.getName().equals("xicy.bank.TestAuto")));
        System.out.println("2.Get xicy.bank.Customer LastName : " + (cus.getLastName().equals("xicy.bank.TestAuto")));
        System.out.println("3.Get xicy.bank.Customer FullName : " + (cus.getFullName().equals("xicy.bank.TestAuto xicy.bank.TestAuto")));
        System.out.println("4.Get xicy.bank.Customer xicy.bank.Sex : " + (cus.getSex().equals(Sex.Undefined)));
        System.out.println("5.Get xicy.bank.Customer BirthDay : " + isTimeEqual(cus.getBirthDay(), getDate(1, 1, 2000)));
        System.out.println();
        System.out.println("6.Set xicy.bank.Customer Name : " + (cus.setName("xicy.bank.TestAuto 1")));
        System.out.println("7.Set xicy.bank.Customer LastName : " + (cus.setLastName("xicy.bank.TestAuto 2")));
        System.out.println("8.Get xicy.bank.Customer xicy.bank.Sex : " + (cus.setSex(Sex.Female)));
        System.out.println("9.Get xicy.bank.Customer BirthDay : " + (cus.setBirthDay(getDate(12, 12, 2012))));
        System.out.println();
        System.out.println("10.Get xicy.bank.Customer Name : " + (cus.getName().equals("xicy.bank.TestAuto 1")));
        System.out.println("11.Get xicy.bank.Customer LastName : " + (cus.getLastName().equals("xicy.bank.TestAuto 2")));
        System.out.println("12.Get xicy.bank.Customer FullName : " + (cus.getFullName().equals("xicy.bank.TestAuto 1 xicy.bank.TestAuto 2")));
        System.out.println("13.Get xicy.bank.Customer xicy.bank.Sex : " + (cus.getSex().equals(Sex.Female)));
        System.out.println("14.Get xicy.bank.Customer BirthDay : " + isTimeEqual(cus.getBirthDay(), getDate(12, 12, 2012)));
        System.out.println();
        System.out.println("---xicy.bank.Customer xicy.bank.TestAuto Ended---");
    }

    public static void testCustomer(Customer cus) {
        System.out.println();
        double cusMoney = cus.getBalance();
        System.out.println("\tDraw xicy.bank.Customer Money : " + (cus.drawMoney(1)));
        System.out.println("\tGet xicy.bank.Customer Balance : " + (cus.getBalance() == (cusMoney = cusMoney - 1)));
        System.out.println("\tDeposit xicy.bank.Customer Money : " + (cus.depositMoney(2)));
        System.out.println("\tGet xicy.bank.Customer Balance : " + (cus.getBalance() == cusMoney + 2));
    }

    public static void testBank(Bank bank) {
        System.out.println();
        System.out.println("---xicy.bank.Bank xicy.bank.TestAuto Starting---");
        System.out.println();
        System.out.println("1.Get xicy.bank.Bank Name : " + (bank.getName().equals("xicy.bank.TestAuto xicy.bank.Bank")));
        System.out.println("2.Set xicy.bank.Bank Name : " + (bank.setName("xicy.bank.TestAuto 2 xicy.bank.Bank")));
        System.out.println("3.Get xicy.bank.Bank Name : " + (bank.getName().equals("xicy.bank.TestAuto 2 xicy.bank.Bank")));
        System.out.println();
        System.out.println("4.Get xicy.bank.Customer Count : " + (bank.getCustomerCount() == 0));
        System.out.println("5.Get Last xicy.bank.Customer ID : " + (bank.getLastCustomerId() == -1));
        System.out.println("6.Get All Customers : " + bank.getAllCustomers());
        System.out.println();
        System.out.println("7.Add xicy.bank.Customer : " + bank.addCustomer(personUmutAkkaya, 10));
        System.out.println("8.Add xicy.bank.Customer : " + bank.addCustomer(personUmutKaya, 20));
        System.out.println("9.Add xicy.bank.Customer : " + bank.addCustomer(personDevrimNazAkdas, 30));
        System.out.println("10.Add xicy.bank.Customer : " + bank.addCustomer(personEmreSatir, 40));
        System.out.println();
        System.out.println("11.Get All Customers : ");
        printAllCustomer(bank.getAllCustomers());
        System.out.println("12.Search Customers : ");
        printAllCustomer(bank.searchCustomersWithFullName("Ak"));
        System.out.println();
        System.out.println("13.Get xicy.bank.Customer Count : " + (bank.getCustomerCount() == 4));
        System.out.println("14.Get Last xicy.bank.Customer ID : " + (bank.getLastCustomerId() == 3));
        System.out.println();
        System.out.println("15.Get xicy.bank.Customer With ID : " + bank.getCustomer(2));
        System.out.println("16.Remove xicy.bank.Customer With ID: " + bank.removeCustomer(2));
        System.out.println("17.Get Removed xicy.bank.Customer : " + (bank.getCustomer(2) == null));
        System.out.println("18.Add Removed xicy.bank.Customer : " + bank.addCustomer(personUmutKaya, 50));
        System.out.println();
        System.out.println("19.Get All Customers: ");
        printAllCustomer(bank.getAllCustomers());

        testCustomer(bank.getCustomer(1));

        System.out.println();
        System.out.println("---xicy.bank.Bank xicy.bank.TestAuto Ended---");
    }

    private static void testLimitedBank(Bank bank) {
        System.out.println();
        System.out.println("---Limited xicy.bank.Bank xicy.bank.TestAuto Starting---");
        System.out.println();
        System.out.println("1.Get xicy.bank.Customer Count : " + (bank.getCustomerCount() == 0));
        System.out.println("2.Get Last xicy.bank.Customer ID : " + (bank.getLastCustomerId() == -1));
        System.out.println("3.Get All Customers : " + bank.getAllCustomers());
        System.out.println();
        System.out.println("4.Add xicy.bank.Customer : " + bank.addCustomer(personUmutAkkaya, 60));
        System.out.println("5.Add xicy.bank.Customer : " + bank.addCustomer(personUmutKaya, 70));
        System.out.println("6.Add xicy.bank.Customer : " + bank.addCustomer(personDevrimNazAkdas, 80));
        System.out.println("7.Add xicy.bank.Customer : " + (!bank.addCustomer(personEmreSatir, 90)));
        System.out.println();
        System.out.println("8.Get All Customers : ");
        printAllCustomer(bank.getAllCustomers());
        System.out.println("9.Search Customers : ");
        printAllCustomer(bank.searchCustomersWithFullName("Ak"));
        System.out.println();
        System.out.println("10.Get xicy.bank.Customer Count : " + (bank.getCustomerCount() == 3));
        System.out.println("11.Get Last xicy.bank.Customer ID : " + (bank.getLastCustomerId() == 2));
        System.out.println();
        System.out.println("12.Get xicy.bank.Customer With ID : " + (bank.getCustomer(0) == bank.searchCustomersWithFullName("Umut Akkaya").entrySet().iterator().next().getValue()));
        System.out.println("13.Remove xicy.bank.Customer With ID : " + bank.removeCustomer(1));
        System.out.println("14.Get Removed xicy.bank.Customer : " + (bank.getCustomer(1) == null));
        System.out.println("15.Add Removed xicy.bank.Customer : " + bank.addCustomer(personUmutAkkaya, 100));
        System.out.println();
        System.out.println("16.Get All Customers : ");
        printAllCustomer(bank.getAllCustomers());

        testCustomer(bank.getCustomer(2));

        System.out.println();
        System.out.println("---Limited xicy.bank.Bank xicy.bank.TestAuto Ended---");
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
