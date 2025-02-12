import java.util.Scanner;
public class Zenn {
    private static int taskCount = 0;
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void main(String[] args) {;
        String logo = " ____ ____ _    _ _    _\n"
                + "|_ _ |  __| \\  | | \\  | |\n"
                + "  / /| |__|  \\ | |  \\ | |\n"
                + " / / |  __|   \\| |   \\| |\n"
                + "/ /_ | |__| |\\   | |\\   |\n"
                + "|____|____|_| \\ _|_| \\ _|\n";
        System.out.println("Hello! I'm Zenn\n" + logo + "\n How may I help you today?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(">");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye bye! See you soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here's your todo list");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index]);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                if (taskCount < MAX_TASKS) {
                    tasks[taskCount] = new Task(input);
                    taskCount++;
                    System.out.println("You've added: " + input);
                }
            }
        }
        scanner.close();
    }
}

