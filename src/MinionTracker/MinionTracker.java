package MinionTracker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Harshveer Singh
 *
 * MinionTracker application to keep track of number of evil deeds
 * done by each individual Minion.
 */
public class MinionTracker {

    // Single shared scanner for MinionTracker application, because use
    // of multiple scanners may cause undesirable issues
    public static Scanner scanner = new Scanner(System.in);

    // Setting up variables for Text Menu
    private static final String TITLE = "Minion Tracker";
    private static final String[] OPTIONS = {"Show all Minions", "Add Minion",
            "Remove Minion", "Add evil deed to Minion", "Exit"};

    // Handling try-catch with following variables
    // Be careful, set values correspond to switch statements
    // in getInputOfType(some_args) method of class Menu.java
    private static final int INTEGER_TYPE = 1;
    private static final int DOUBLE_TYPE = 2;

    public static void main(String[] args) {

        ArrayList<Minion> minionList = new ArrayList<>();

        // Adding dummy Minions for ease of testing
        // Remove dummy Minions before application deployment
        minionList.add(new Minion("Cool Minion", 4));
        minionList.add(new Minion("Crazy Minion", 4.4));

        Menu menu = new Menu(TITLE, OPTIONS);

        boolean runProgram = true;
        while (runProgram) {

            switch (menu.getSelection(scanner)) {

                case 1:
                    handleMenuOption1(minionList);
                    break;

                case 2:
                    handleMenuOption2(scanner, minionList);
                    break;

                case 3:
                    handleMenuOption3(scanner, minionList);
                    break;

                case 4:
                    handleMenuOption4(scanner, minionList);
                    break;

                case 5:
                    runProgram = handleMenuOption5(scanner);
                    break;
            }
        }
        scanner.close();
    }

    private static boolean handleMenuOption5(Scanner scanner) {
        System.out.println("Are you sure you want to exit?(y)");
        if (scanner.nextLine().equals("y")) {
            System.out.println("Exiting application.");
            return false;
        }
        else {
            System.out.println("Returning to menu.");
        }
        return true;
    }

    private static void handleMenuOption4(Scanner scanner, ArrayList<Minion> minionList) {
        displayAllMinions(minionList);

        // Get user input, to select a Minion to add an evil deed to that Minion
        int deedMinionIndex = Integer.parseInt(Menu.getInputOfType(INTEGER_TYPE, "Please " +
                "enter a number to add one evil deed to a minion corresponding to that number.",
                "Please enter a number in range 1 - "+minionList.size(), scanner))-1;

        if (deedMinionIndex >= 0 && deedMinionIndex < minionList.size()) {
            System.out.println("Are you sure you want to add one evil deed to a minion?(y)");
            if (scanner.nextLine().trim().equals("y")) {
                minionList.get(deedMinionIndex).incEvilDeeds();
                System.out.println("Successfully incremented evil deed by one.");
            } else {
                System.out.println("Invalid value. Evil deeds unchanged.");
            }
        } else {
            System.out.println("Invalid value. Evil deeds unchanged.");
        }
    }

    private static void handleMenuOption3(Scanner scanner, ArrayList<Minion> minionList) {
        displayAllMinions(minionList);

        // Get user input, to select a Minion to be removed
        int delMinionIndex = Integer.parseInt(Menu.getInputOfType(INTEGER_TYPE, "Please enter a " +
                    "number to remove a Minion corresponding to that number.",
                "Please enter a number in range 1 - "+ minionList.size(), scanner))-1;

        if (delMinionIndex >= 0 && delMinionIndex < minionList.size()) {
            System.out.println("Are you sure you want to delete minion?(y)");
            if (scanner.nextLine().trim().equals("y")) {
                minionList.remove(delMinionIndex);
                System.out.println("Minion successfully deleted. ");
            } else {
                System.out.println("Invalid value. Minion not deleted.");
            }
        } else {
            System.out.println("Invalid value. Minion not deleted.");
        }
    }

    private static void handleMenuOption2(Scanner scanner, ArrayList<Minion> minionList) {
        System.out.println("Please enter minion's name: ");
        String name = scanner.nextLine();
        Double height = Double.parseDouble(Menu.getInputOfType(DOUBLE_TYPE, "Please enter minion's" +
                " height : " , "Please enter a number like 5.7", scanner));

        minionList.add(new Minion(name, height));
        System.out.println("Minion successfully added.");
    }

    private static void handleMenuOption1(ArrayList<Minion> minionList) {
        displayAllMinions(minionList);
    }

    private static void displayAllMinions(ArrayList<Minion> minionList) {
        if (minionList.size() == 0) {
            System.out.println("No Minions in the list.");
        } else {
            for (int i=0; i<minionList.size(); i++) {
                System.out.println((i+1)+" "+minionList.get(i));
            }
        }
    }
}
