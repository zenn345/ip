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
        String[] history = new String[100];
        int taskCount = 0;
        String command;

        while (true) {
            System.out.println(">");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye bye! See you soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("Here's your todo list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(history[i]);
                }
            } else {
                if (taskCount < 100) {
                    history[taskCount] = command;
                    taskCount++;
                    System.out.println("You've added: " + command);
                }
            }
        }
        scanner.close();
    }
}

