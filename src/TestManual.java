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
        printHelpMain();
        do {
            command = scaner.nextLine().toLowerCase().split("\\s+");

            if (selectedBank == null) switch (command[0]) {
                case "addbank":
                    addBank(command);
                    break;
                case "removebank":
                    removeBank(command);
                    break;
                case "listbank":
                    listBanks();
                    break;
                case "selectbank":
                    selectBank(command);
                    break;
                case "exit":
                    break;
                case "help":
                default:
                    printHelpMain();
                    break;
            }
            else if (selectedCustomer == null) switch (command[0]) {
                case "addcustomer":
                    addCustomer(command);
                    break;
                case "removecustomer":
                    removeCustomer(command);
                    break;
                case "selectcustomer":
                    selectCustomer(command);
                    break;
                case "listcustomer":
                    listCustomer();
                    break;
                case "getallhistory":
                    getAllHistory();
                    break;
                case "return":
                    selectBank(new String[]{"selectBank", "-1"});
                    break;
                case "exit":
                    break;
                case "help":
                default:
                    printHelpSelectedBank();
                    break;
            }
            else switch (command[0]) {
                    case "return":
                        selectCustomer(new String[]{"selectcustomer", "-1"});
                        break;
                    case "exit":
                        break;
                    case "help":
                    default:
                        printHelpCustomer();
                        break;
                }

        } while (!command[0].equals("exit"));
    }

    private static void printHelpCustomer() {
        System.out.println("customer");
    }

    private static void printHelpSelectedBank() {
        System.out.println("bank");
    }

    private static void printHelpMain() {
        System.out.println("addBank\t<Name:String>\tAdd bank to collection");
        System.out.println("removeBank\t<ID:Long>\tRemove bank by id");
        System.out.println("listBank\tList all bank");
        System.out.println("exit\tClose to program");
    }

    private static boolean checkArgs(String[] args, int argCount) {
        if (args.length >= argCount + 1) {
            return true;
        } else {
            System.out.println("Wrong argument.\nPlease check " + args[0] + " on help");
            return false;
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
        if (checkArgs(args, 1))
            banks.put(bankId++, new Bank(capitalize(args[1])));
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
        if (checkArgs(args, 4) && isNumeric(args[4]))
            selectedBank.addCustomer(new Person(capitalize(args[1]), capitalize(args[2]), Sex.valueOf(capitalize(args[3])), new Date(0)), Double.parseDouble(args[4]));

    }

    private static void removeCustomer(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            selectedBank.removeCustomer(Long.parseLong(args[1]));
        else
            System.out.println("Please enter only numeric for this command");
    }

    private static void listCustomer() {
        printCustomers(selectedBank.getAllCustomers());
    }

    private static void selectCustomer(String[] args) {
        if (checkArgs(args, 1) && isNumeric(args[1]))
            selectedCustomer = selectedBank.getCustomer(Long.parseLong(args[1]));
        else
            System.out.println("Please enter only numeric for this command");
    }

    private static void getAllHistory() {
        printHistory(selectedBank.getAllHistory());
    }
}
