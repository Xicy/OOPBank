package xicy.bank.tests;

import xicy.bank.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Umut Akkaya on 21.03.2017.
 */
//TODO Set Errors
public class TestManual {
    private static final BankCollection banks = new BankCollection();
    private static Bank selectedBank = null;
    private static Customer selectedCustomer = null;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Scanner scaner = new Scanner(System.in);
        String[] command;
        printHelp();
        do {
            command = translateCommandline(scaner.nextLine());
            if (command.length < 1) continue;
            command[0] = command[0].toLowerCase();
            if (command[0].equals("exit")) break;
            else if (command[0].equals("clear")) clearScreen();
            else if (command[0].equals("help")) printHelp();
            else if (selectedBank == null) switch (command[0]) {
                case "add":
                    addBank(command);
                    break;
                case "remove":
                    removeBank(command);
                    break;
                case "list":
                    listBanks();
                    break;
                case "select":
                    selectBank(command);
                    break;
                default:
                    printHelp();
                    break;
            }
            else if (selectedCustomer == null) switch (command[0]) {
                case "add":
                    addCustomer(command);
                    break;
                case "remove":
                    removeCustomer(command);
                    break;
                case "list":
                    listCustomer(command);
                    break;
                case "select":
                    selectCustomer(command);
                    break;
                case "history":
                    getAllHistory(command);
                    break;
                case "customer":
                    getCustomer(command);
                    break;
                case "name":
                    nameBank(command);
                    break;
                case "return":
                    selectBank(new String[]{"selectBank", "-1"});
                    break;
                default:
                    printHelp();
                    break;
            }
            else switch (command[0]) {
                    case "name":
                        nameCustomer(command);
                        break;
                    case "sex":
                        sexCustomer(command);
                        break;
                    case "birthday":
                        birthdayCustomer(command);
                        break;
                    case "draw":
                        drawMoney(command);
                        break;
                    case "deposit":
                        depositMoney(command);
                        break;
                    case "balance":
                        balanceCustomer();
                        break;
                    case "history":
                        getAllHistory(new String[]{"history", "" + selectedCustomer.getId()});
                        break;
                    case "return":
                        selectCustomer(new String[]{"selectcustomer", "-1"});
                        break;
                    default:
                        printHelp();
                        break;
                }

        } while (command.length < 1 || !command[0].equals("exit"));
    }

    private static void printLine(String text, int size) {
        String lr = new String(new char[(size - text.length()) / 2]).replace("\0", "-");
        System.out.println(lr + text + lr);
    }

    private static void printHelp() {
        if (selectedBank == null) {
            printLine("Bank System", 80);
            System.out.println("add\t\t\t<Name:String>\tAdd non limited bank to collection");
            System.out.println("add\t\t\t<Name:String> <Capacity:Long>\tAdd limited bank to collection");
            System.out.println("remove\t\t<ID:Long>\tRemove bank by id");
            System.out.println("list\t\tList all bank");
            System.out.println("select\t\t<ID:Long>\tSelect bank");
        } else if (selectedCustomer == null) {
            printLine("Bank System of " + selectedBank.getName(), 80);
            System.out.println("add\t\t\t<Name:String> <LastName:String> <Sex:Undefined/Male/Female> <BirthDate:dd.mm.yyyy:String> <StartingBalance:Double>\tAdd customer");
            System.out.println("remove\t\t<ID:Long>\tRemove customer by id");
            System.out.println("list\t\tList all customers");
            System.out.println("list\t\t<Name:String>\tSearch customer by full name");
            System.out.println("select\t\t<ID:Long>\tSelect customer by id");
            System.out.println("history\t\tGet all history of bank");
            System.out.println("history\t\t<ID:Long>\tGet all history of Customer");
            System.out.println("customer\t<ID:Long>\tGet customer of bank by id");
            System.out.println("name\t\tGet bank name");
            System.out.println("name\t\t<Name:String>\tSet bank name");
            System.out.println("return\t\tUpward direction");
        } else {
            printLine("Customer System of " + selectedCustomer.getFullName(), 80);
            System.out.println("name\t\tGet customer name");
            System.out.println("name\t\t<Name:String>\t<LastName:String>\tSet customer name");
            System.out.println("sex\t\t\tGet customer sex");
            System.out.println("sex\t\t\t<Sex:Undefined/Male/Female>\tSet customer sex");
            System.out.println("birthday\tGet customer birthday");
            System.out.println("birthday\t<BirthDate:dd.mm.yyyy:String>\tSet customer birthday");
            System.out.println("draw\t\tDraw money");
            System.out.println("deposit\t\tdeposit money");
            System.out.println("balance\t\tGet balance");
            System.out.println("history\t\tGet history of customer");
            System.out.println("return\t\tUpward direction");
        }
        System.out.println("help\t\tShow help text");
        System.out.println("exit\t\tClose to program");
        printLine("", 80);
    }

    private static String[] translateCommandline(String toProcess) {
        if (toProcess == null || toProcess.length() == 0) return new String[0];

        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = normal;
        final StringTokenizer tok = new StringTokenizer(toProcess, "\"\' ", true);
        final ArrayList<String> result = new ArrayList<>();
        final StringBuilder current = new StringBuilder();
        boolean lastTokenHasBeenQuoted = false;

        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            switch (state) {
                case inQuote:
                    if ("\'".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                case inDoubleQuote:
                    if ("\"".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                default:
                    if ("\'".equals(nextTok)) {
                        state = inQuote;
                    } else if ("\"".equals(nextTok)) {
                        state = inDoubleQuote;
                    } else if (" ".equals(nextTok)) {
                        if (lastTokenHasBeenQuoted || current.length() != 0) {
                            result.add(current.toString());
                            current.setLength(0);
                        }
                    } else {
                        current.append(nextTok);
                    }
                    lastTokenHasBeenQuoted = false;
                    break;
            }
        }
        if (lastTokenHasBeenQuoted || current.length() != 0) {
            result.add(current.toString());
        }
        if (state == inQuote || state == inDoubleQuote) {
            throw new RuntimeException("unbalanced quotes in " + toProcess);
        }
        return result.toArray(new String[result.size()]);
    }

    private static boolean checkArgs(String[] args, int argCount) {
        return args.length == argCount + 1;
    }

    private static void clearScreen() {
        try {
            final String os = System.getProperty("os.nameBank");
            if (os.contains("Windows"))
                Runtime.getRuntime().exec("cls");
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static void printCustomers(Map<Long, Customer> customers) {
        for (Map.Entry<Long, Customer> cus : customers.entrySet())
            System.out.println("\t" + cus.getKey() + ". " + cus.getValue());
    }

    private static void printHistory(Map<Long, History> histories) {
        for (Map.Entry<Long, History> his : histories.entrySet()) {
            System.out.println("\t" + his.getKey() + ". [" + his.getValue().getTime() + "] [" + his.getValue().getEvent() + "] " + his.getValue().getCustomer() + " " + his.getValue().getInfo());
        }
    }

    private static String capitalize(String text) {
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    private static void addBank(String[] args) {
        if (checkArgs(args, 2) && isNumeric(args[2])) {
            if (banks.add(new Bank(args[1], Long.parseLong(args[2]))))
                System.out.println("Bank Added");
            else
                System.out.println("Error:Bank was not added");
        } else if (checkArgs(args, 1)) {
            if (banks.add(new Bank(args[1])))
                System.out.println("Bank Added");
            else
                System.out.println("Error:Bank was not added");
        } else
            System.out.println("Error:Argument is wrong\nPlease check help");
    }

    private static void removeBank(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1])) {
            if (banks.remove(Long.parseLong(args[1])))
                System.out.println("Bank Removed");
            else
                System.out.println("Bank was not removed");
        } else
            System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void listBanks() {
        printLine("", 80);
        for (Map.Entry<Long, Bank> bank : banks.getAll().entrySet()) {
            System.out.println("[" + bank.getKey() + "] " + bank.getValue().getName());
        }
        printLine("", 80);
    }

    private static void selectBank(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1])) {
            selectedBank = banks.get(Long.parseLong(args[1]));
            if (selectedBank == null && Long.parseLong(args[1]) != -1)
                System.out.println("Error:Bank was not selected");
            else if (Long.parseLong(args[1]) != -1)
                System.out.println("Bank selected");
            else System.out.println("Returned");
        } else
            System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void addCustomer(String[] args) {
        if (checkArgs(args, 5) && isNumeric(args[5])) {
            try {
                selectedBank.addCustomer(new Person(args[1], args[2], Sex.valueOf(capitalize(args[3])), new SimpleDateFormat("dd.MM.yyyy").parse(args[4].replace('/', '.').replace('\\', '\\').replace(' ', '.'))), Double.parseDouble(args[5]));
            } catch (ParseException e) {
                System.out.println("Error:Customer was not added");
            }
        } else
            System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void removeCustomer(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            if (selectedBank.removeCustomer(Long.parseLong(args[1])))
                System.out.println("Customer removed");
            else
                System.out.println("Customer was not removed");
        else
            System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void listCustomer(String[] args) {
        printLine("", 80);
        if (checkArgs(args, 1))
            printCustomers(selectedBank.searchCustomersWithFullName(args[1]));
        else
            printCustomers(selectedBank.getAllCustomers());
        printLine("", 80);
    }

    private static void selectCustomer(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1])) {
            selectedCustomer = selectedBank.getCustomer(Long.parseLong(args[1]));
            if (selectedBank == null && Long.parseLong(args[1]) != -1)
                System.out.println("Bank was not selected");
            else if (Long.parseLong(args[1]) != -1)
                System.out.println("Bank selected");
            else System.out.println("Returned");
        } else
            System.out.println("Please enter only numeric for this command");
    }

    private static void getAllHistory(String[] args) {
        printLine("", 80);
        if (checkArgs(args, 1) && isNumeric(args[1]))
            printHistory(selectedBank.getHistoryOfUser(Long.parseLong(args[1])));
        else
            printHistory(selectedBank.getAllHistory());
        printLine("", 80);
    }

    private static void nameBank(String[] args) {
        if (checkArgs(args, 1))
            if (selectedBank.setName(args[1]))
                System.out.println("Bank name setted");
            else
                System.out.println("Bank name was not setted");
        else if (checkArgs(args, 0))
            System.out.println("Bank Name:" + selectedBank.getName());
        else System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void nameCustomer(String[] args) {
        if (checkArgs(args, 2)) {
            if (selectedCustomer.setName(args[1]))
                System.out.println("Customer name setted");
            else
                System.out.println("Customer name was not setted");
            if (selectedCustomer.setLastName(args[2]))
                System.out.println("Customer lastname setted");
            else
                System.out.println("Customer lastname was not setted");
        } else if (checkArgs(args, 0))
            System.out.println("Customer Name:" + selectedCustomer.getFullName());
        else System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void birthdayCustomer(String[] args) {
        if (checkArgs(args, 1))
            try {
                selectedCustomer.setBirthDay(new SimpleDateFormat("dd.MM.yyyy").parse(args[1].replace('/', '.').replace('\\', '\\').replace(' ', '.')));
            } catch (ParseException e) {
                System.out.println("Error:BirthDay was not setted");
            }
        else if (checkArgs(args, 0))
            System.out.println("Customer Birthday:" + selectedCustomer.getBirthDay());
        else System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void balanceCustomer() {
        System.out.println("Balance : " + selectedCustomer.getBalance());
    }

    private static void drawMoney(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            if (selectedCustomer.drawMoney(Double.parseDouble(args[1])))
                System.out.println("Money Drawed");
            else System.out.println("Money not enough");
        else System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void depositMoney(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            if (selectedCustomer.depositMoney(Double.parseDouble(args[1])))
                System.out.println("Money Deposited");
            else System.out.println("Money was not deposited");
        else System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void sexCustomer(String[] args) {
        if (checkArgs(args, 1))
            if (selectedCustomer.setSex(Sex.valueOf(capitalize(args[1]))))
                System.out.println("Sex setted");
            else System.out.println("Sex was not setted");
        else if (checkArgs(args, 0))
            System.out.println("Customer Sex : " + selectedCustomer.getSex());
        else System.out.println("Error:Argument is wrong\nPlease check Help");
    }

    private static void getCustomer(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            System.out.println(selectedBank.getCustomer(Long.parseLong(args[1])));
        else System.out.println("Error:Argument is wrong\nPlease check Help");
    }
}