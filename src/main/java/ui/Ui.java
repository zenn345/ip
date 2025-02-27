package ui;

import java.util.Scanner;
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showWelcome() {
        String logo = " ____ ____ _    _ _    _\n"
                + "|_ _ |  __| \\  | | \\  | |\n"
                + "  / /| |__|  \\ | |  \\ | |\n"
                + " / / |  __|   \\| |   \\| |\n"
                + "/ /_ | |__| |\\   | |\\   |\n"
                + "|____|____|_| \\ _|_| \\ _|\n";
        System.out.println("Hello! I'm \n" + logo + "\n What you need help with?");
    }

    public void showGoodbye() {
        System.out.println("Bye! See you again ah!");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
