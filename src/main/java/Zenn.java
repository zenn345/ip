import java.util.Scanner;
import java.util.ArrayList;
public class Zenn {
    //private static int taskCount = 0;
    //private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {;
        String logo = " ____ ____ _    _ _    _\n"
                + "|_ _ |  __| \\  | | \\  | |\n"
                + "  / /| |__|  \\ | |  \\ | |\n"
                + " / / |  __|   \\| |   \\| |\n"
                + "/ /_ | |__| |\\   | |\\   |\n"
                + "|____|____|_| \\ _|_| \\ _|\n";
        System.out.println("Hello! I'm \n" + logo + "\n What you need help with?");

        tasks = Storage.loadTasks();
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
                    listTasks();
                } else if (input.startsWith("todo ")) {
                    addTodo(parts);
                } else if (input.startsWith("deadline ")) {
                    addDeadline(parts);
                } else if (input.startsWith("event ")) {
                    addEvent(parts);
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        Storage.saveTasks(tasks);
                        System.out.println("Nice! Task done liao:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        throw new ZennException("Invalid task number.");
                    }
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).unmarkAsDone();
                        Storage.saveTasks(tasks);
                        System.out.println("walao, haven't do finish:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        throw new ZennException("Invalid task number.");
                    }
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) -1;
                    if (index >= 0 && index < tasks.size()) {
                        Task removedTask = tasks.remove(index);
                        Storage.saveTasks(tasks);
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

    private static void listTasks() {
        System.out.println("Your very very long todo list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void addTodo(String[] parts) throws ZennException {
        if (parts.length < 2) {
            throw new ZennException("Eh! Description cannot be empty la");
        }
        Task newTask = new Todo(parts[1]);
        tasks.add(newTask);
        Storage.saveTasks(tasks);
        printTaskAdded(newTask);
    }

    private static void addDeadline(String [] parts) throws ZennException {
        if (parts.length < 2 || !parts[1].contains("/by")) {
            throw new ZennException("Incorrect format. Use: deadline <task> /by <time>");
        }
        String[] deadlineParts = parts[1].split("/by ", 2);
        Task newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
        tasks.add(newTask);
        Storage.saveTasks(tasks);
        printTaskAdded(newTask);
    }

    private static void addEvent(String[] parts) throws ZennException {
        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
            throw new ZennException("Incorrect format. Use: event <task> /from <start> /to <end>");
        }
        String[] eventParts = parts[1].split(" /from | /to ", 3);
        Task newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
        tasks.add(newTask);
        Storage.saveTasks(tasks);
        printTaskAdded(newTask);
    }

    private static void printTaskAdded(Task task) {
        System.out.println("Ok, added liao:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the todo list. (stop procrastinating!)");
    }
}