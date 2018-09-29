package MinionTracker;

import java.util.Scanner;

/**
 * Class to manage and display text menu so user can
 * interact with MinionTracker application
 */
public class Menu {

    // Text menu attributes
    private String title;
    private String[] options;

    // Handling try-catch with following variables
    // Be careful, set values correspond to switch statements
    // in getInputOfType(some_args) method of class Menu.java
    private static final int INTEGER_TYPE = 1;
    private static final int DOUBLE_TYPE = 2;

    private final String ENTER_NUM = "Please enter a number in range ";

    // Error codes
    private static final int UNEXPECTED_INPUT_TYPE = -5;

    /**
     * Menu constructor
     *
     * @param title title of this menu
     * @param options options in this menu
     */
    Menu(String title, String[] options) {
        this.title = title;
        this.options = options;
    }

    /**
     * Displays menu title along with available
     * options in the menu
     */
    public void display() {
        // Display title in rectangular asterisks
        print_asterisks(title.length()+2);
        System.out.println("*"+title+"*");
        print_asterisks(title.length()+2);

        // Display numbered options starting from one
        for (int i=0; i<options.length; i++) {
            System.out.println(i+1+" "+options[i]);
        }
        System.out.println();
    }

    public int getSelection(Scanner scanner) {
        return getNumberBetween(scanner,1, options.length);
    }

    private int getNumberBetween(Scanner scanner, int startPoint, int endPoint) {
        boolean requireInput = true;
        int input = -1;
        while (requireInput) {
            display();
            input = Integer.parseInt(getInputOfType(INTEGER_TYPE, null,
                    ENTER_NUM+startPoint+" - "+endPoint, scanner));
            if (input>=startPoint && input<=endPoint) {
                requireInput = false;
            }
        }
        return input;
    }

    private void print_asterisks(int length) {
        for (int i=0; i<length; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public String getTitle() {
        return title;
    }

    public String[] getOptions() {
        return options;
    }

    /**
     * Get a valid input, of type Integer or Double in a
     * String format to avoid stopping the program when
     * parsing Integer or Double input received from user
     *
     * @param dataType dataType of user input to be received and confirmed
     *                 '1' means Integer_type and '2' means Double_integer_type
     * @param ask prints the value to console before getting user input, skipped if null
     * @param askIfWrong prints the value to console if incorrect user input is
     *                   received, skipped if null, but user is still expected to enter
     *                   a valid value or the program will not continue
     * @param scanner Scanner object to be used for console input/output
     * @return a String object guranteed to have either Integer or Double equivalent
     * value depending on value of dataType provided to the method
     */
    public static String getInputOfType(int dataType, String ask, String askIfWrong, Scanner scanner) {
        boolean requireInput = true;
        while(requireInput) {
            switch (dataType) {
                // Get input of type integer
                case 1:
                    try {
                        printIfNotNull(ask);
                        int input = Integer.parseInt(scanner.nextLine().trim());
                        requireInput = false;
                        return String.valueOf(input);

                    } catch (Exception e){
                        printIfNotNull(askIfWrong);
                    }
                    break;

                // Get input of type double
                case 2:
                    try {
                        printIfNotNull(ask);
                        double input = Double.parseDouble(scanner.nextLine().trim());
                        requireInput = false;
                        return String.valueOf(input);

                    } catch (Exception e){
                        printIfNotNull(askIfWrong);
                    }
                    break;

                default:
                    try {
                        throw new Exception("unexpected input type");
                    } catch (Exception e) {
                        e.printStackTrace();
                        requireInput = false;
                        System.exit(UNEXPECTED_INPUT_TYPE);
                    }
            }
        }
        return "Should not have reached here :-(";
    }

    private static void printIfNotNull(String string) {
        if (string != null) {
            System.out.println(string);
        }
    }
}
