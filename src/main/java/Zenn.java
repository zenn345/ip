import java.util.Scanner;
import java.util.ArrayList;
public class Zenn {
    //private static int taskCount = 0;
    //private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {;
        String logo = " ____ ____ _    _ _    _\n"
                + "|_ _ |  __| \\  | | \\  | |\n"
                + "  / /| |__|  \\ | |  \\ | |\n"
                + " / / |  __|   \\| |   \\| |\n"
                + "/ /_ | |__| |\\   | |\\   |\n"
                + "|____|____|_| \\ _|_| \\ _|\n";
        System.out.println("Hello! I'm \n" + logo + "\n What you need help with?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println(">");
                String input = scanner.nextLine();
                String[] parts = input.split(" ", 2);

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye! See you again ah!");
                    break;
                } else if (input.startsWith("list")) {
                    System.out.println("Your very very long todo list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (input.startsWith("todo ")) {
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ZennException("Eh! Description cannot be empty la");
                    }
                    addTodo(parts[1]);
                } else if (input.startsWith("deadline ")) {
                    String[] deadlineParts = parts[1].split("/by ", 2);
                    if (deadlineParts.length < 2) {
                        throw new ZennException("Incorrect format. Use: deadline <task> /by <time>");
                    }
                    addDeadline(deadlineParts[0], deadlineParts[1]);
                } else if (input.startsWith("event ")) {
                    String[] eventParts = parts[1].split(" /from | /to ", 3);
                    if (eventParts.length < 3) {
                        throw new ZennException("Incorrect format. Use: event <task> /from <start> /to <end>");
                    }
                    addEvent(eventParts[0], eventParts[1], eventParts[2]);
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println("Nice! Task done liao:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        throw new ZennException("Invalid task number.");
                    }
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).unmarkAsDone();
                        System.out.println("walao, haven't do finish:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        throw new ZennException("Invalid task number.");
                    }
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) -1;
                    if (index >= 0 && index < tasks.size()) {
                        Task removedTask = tasks.remove(index);
                        System.out.println("Yay! Settle liao.");
                        System.out.println(" " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        throw new ZennException("Invalid task number.");
                    }
                } else {
                    throw new ZennException("don't know what you saying");
                }
            } catch (ZennException e) {
                System.out.println(" " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("enter valid task number leh");
            }
        }
        scanner.close();
    }

    private static void addTodo(String description) {
        tasks.add(new Todo(description));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void printTaskAdded(Task task) {
        System.out.println("Ok, added liao:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the todo list. (stop procrastinating!)");
    }
}