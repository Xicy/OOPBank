package xicy.bank;

import java.util.*;

/**
 * Created by Umut Akkaya on 21.03.2017.
 */
public class TestManual {
    private static Scanner scaner;
    private static Map<Long, Bank> banks = new HashMap();
    private static long bankId = 0;
    private static Bank selectedBank = null;
    private static Customer selectedCustomer = null;

    public static void main(String[] args) {

        scaner = new Scanner(System.in);
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
                case "search":
                    listCustomer(command);
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

    private static void printHelp() {
        if (selectedBank == null) {
            System.out.println("addBank\t<Name:String>\tAdd bank to collection");
            System.out.println("removeBank\t<ID:Long>\tRemove bank by id");
            System.out.println("listBank\tList all bank");
            System.out.println("exit\tClose to program");
            //TODO:main
        } else if (selectedCustomer == null) {
            System.out.println("bank");
            //TODO:bank
        } else {
            System.out.println("customer");
            //TODO:customer
        }
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
        return args.length >= argCount + 1;
    }

    private static void clearScreen() {
        //TODO: daha düzgün
        try {
            final String os = System.getProperty("os.nameBank");
            if (os.contains("Windows"))
                Runtime.getRuntime().exec("cls");
            else
                Runtime.getRuntime().exec("clear");
        } catch (final Exception e) {
            //
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
        if (checkArgs(args, 2) && isNumeric(args[2]))
            banks.put(bankId++, new Bank(args[1], Long.parseLong(args[2])));
        else if (checkArgs(args, 1))
            banks.put(bankId++, new Bank(args[1]));
        else
            System.out.println("Error:xicy.bank.Bank was not added!");
    }

    private static void removeBank(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            banks.remove(Long.parseLong(args[1]));
        else
            System.out.println("Please enter only numeric for this command");
    }

    private static void listBanks() {
        for (Map.Entry<Long, Bank> bank : banks.entrySet()) {
            System.out.println("[" + bank.getKey() + "] " + bank.getValue().getName());
        }
    }

    private static void selectBank(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            selectedBank = banks.getOrDefault(Long.parseLong(args[1]), null);
        else
            System.out.println("Please enter only numeric for this command");
    }

    private static void addCustomer(String[] args) {
        if (checkArgs(args, 5) && isNumeric(args[5]))
            selectedBank.addCustomer(new Person(args[1], args[2], Sex.valueOf(capitalize(args[3])), new Date()), Double.parseDouble(args[5]));

    }

    private static void removeCustomer(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            selectedBank.removeCustomer(Long.parseLong(args[1]));
        else
            System.out.println("Please enter only numeric for this command");
    }

    private static void listCustomer(String[] args) {
        if (checkArgs(args, 1))
            printCustomers(selectedBank.searchCustomersWithFullName(args[1]));
        else
            printCustomers(selectedBank.getAllCustomers());
    }

    private static void selectCustomer(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            selectedCustomer = selectedBank.getCustomer(Long.parseLong(args[1]));
        else
            System.out.println("Please enter only numeric for this command");
    }

    private static void getAllHistory(String[] args) {
        //TODO bakılacak
        if (checkArgs(args, 1) && isNumeric(args[1]))
            printHistory(selectedBank.getHistoryOfUser(Long.parseLong(args[1])));
        else
            printHistory(selectedBank.getAllHistory());
    }

    private static void nameBank(String[] args) {
        if (checkArgs(args, 1))
            selectedBank.setName(args[1]);
        else
            System.out.println("Bank Name:" + selectedBank.getName());
    }

    private static void nameCustomer(String[] args) {
        if (checkArgs(args, 2)) {
            selectedCustomer.setName(args[1]);
            selectedCustomer.setLastName(args[2]);
        } else
            System.out.println("Customer Name:" + selectedCustomer.getFullName());
    }

    private static void birthdayCustomer(String[] args) {
        if (checkArgs(args, 1))
            selectedCustomer.setBirthDay(new Date()); //TODO:
        else
            System.out.println("Customer Birthday:" + selectedCustomer.getBirthDay());
    }

    private static void balanceCustomer() {
        System.out.println("Balance : " + selectedCustomer.getBalance());
    }

    private static void drawMoney(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            selectedCustomer.drawMoney(Double.parseDouble(args[1]));
    }

    private static void depositMoney(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            selectedCustomer.depositMoney(Double.parseDouble(args[1]));
    }

    private static void sexCustomer(String[] args) {
        if (checkArgs(args, 1))
            selectedCustomer.setSex(Sex.valueOf(capitalize(args[1])));
        else
            System.out.println("Customer Sex : " + selectedCustomer.getSex());
    }

    private static void getCustomer(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            System.out.println(selectedBank.getCustomer(Long.parseLong(args[1])));
    }
}