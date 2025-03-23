package zenn.ui;

import java.util.Scanner;

/**
 * Represents the user interface for the Zenn application.
 * This class is responsible for displaying messages to the user and reading user input.
 * It handles various interactions, including welcoming the user, reading commands,
 * displaying messages, and showing error messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object, initializing the scanner to read from standard input
     * and the output to collect messages.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return The command inputted by the user.
     */
    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message and the application's logo to the user.
     */
    public static String showWelcome() {
        String logo = " ____ ____ _    _ _    _\n"
            + "|_ _ |  __| \\  | | \\  | |\n"
            + "  / /| |__|  \\ | |  \\ | |\n"
            + " / / |  __|   \\| |   \\| |\n"
            + "/ /_ | |__| |\\   | |\\   |\n"
            + "|____|____|_| \\ _|_| \\ _|\n";
        return "Hello! I'm Zenn Bot!\n" + "What you need help with?";
    }

    /**
     * Displays a goodbye message to the user when the application is exiting.
     */
    public String showGoodbye() {
        return "Bye! See you again ah!";
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays a general message to user.
     * @param message The message to be displayed.
     */
    public String showMessage(String message) {
        return message;
    }
}