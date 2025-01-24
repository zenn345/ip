import java.util.Scanner;
public class Zenn {
    public static void main(String[] args) {
        String logo = " ____ ____ _    _ _    _\n"
                + "|_ _ |  __| \\  | | \\  | |\n"
                + "  / /| |__|  \\ | |  \\ | |\n"
                + " / / |  __|   \\| |   \\| |\n"
                + "/ /_ | |__| |\\   | |\\   |\n"
                + "|____|____|_| \\ _|_| \\ _|\n";
        System.out.println("Hello! I'm Zenn\n" + logo + "\n How may I help you today?");

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye bye! See you soon!");
                break;
            }

            System.out.println("  " + command);
        }
        scanner.close();
    }
}

