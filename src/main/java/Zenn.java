import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
public class Zenn {

    private static TaskList tasks;
    //private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        ;
        String logo = " ____ ____ _    _ _    _\n"
                + "|_ _ |  __| \\  | | \\  | |\n"
                + "  / /| |__|  \\ | |  \\ | |\n"
                + " / / |  __|   \\| |   \\| |\n"
                + "/ /_ | |__| |\\   | |\\   |\n"
                + "|____|____|_| \\ _|_| \\ _|\n";
        System.out.println("Hello! I'm \n" + logo + "\n What you need help with?");

        tasks = new TaskList(Storage.loadTasks());
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
                    Task task = tasks.getTask(index);
                    task.markAsDone();
                    Storage.saveTasks(tasks.getTasks());
                    System.out.println("Nice! Task done liao:");
                    System.out.println("  " + task);
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task task = tasks.getTask(index);
                    task.unmarkAsDone();
                    Storage.saveTasks(tasks.getTasks());
                    System.out.println("walao, haven't do finish:");
                    System.out.println("  " + task);
                } else if (input.startsWith("on ")) {
                    filterTasksByDate(parts[1]);
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task removedTask = tasks.removeTask(index);
                    Storage.saveTasks(tasks.getTasks());
                    System.out.println("Yay! Settle liao.");
                    System.out.println(" " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
            try {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            } catch (ZennException e) {
                System.out.println("Error retrieving task at index " + i + ": " + e.getMessage());
            }
        }
    }

    private static void addTodo(String[] parts) throws ZennException {
        if (parts.length < 2) {
            throw new ZennException("Eh! Description cannot be empty la");
        }
        Task newTask = new Todo(parts[1]);
        tasks.addTask(newTask);
        Storage.saveTasks(tasks.getTasks());
        printTaskAdded(newTask);
    }

    private static void addDeadline(String[] parts) throws ZennException {
        if (parts.length < 2 || !parts[1].contains("/by ")) {
            throw new ZennException("Incorrect format. Use: deadline <task> /by <time>");
        }
        String[] deadlineParts = parts[1].split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[1].isBlank()) {
            throw new ZennException("Deadline date/time cannot be empty leh pls");
        }

        String description = deadlineParts[0].trim();
        String byString = deadlineParts[1].trim();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

        try {
            LocalDateTime byDateTime = LocalDateTime.parse(byString, inputFormat);
            String formattedDate = byDateTime.format(outputFormat);
            Task newTask = new Deadline(description, byDateTime);
            tasks.addTask(newTask);
            Storage.saveTasks(tasks.getTasks());
            printTaskAdded(newTask);
        } catch (DateTimeParseException e) {
            throw new ZennException("Invalid date format. Use: d/M/yyyy HHmm (eg. 2/12/2022 1800)");
        }
    }

    private static void addEvent(String[] parts) throws ZennException {
        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
            throw new ZennException("Incorrect format. Use: event <task> /from <start> /to <end>");
        }
        String[] eventParts = parts[1].split(" /from | /to ", 3);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(eventParts[1], inputFormat);
            LocalDateTime toDateTime = LocalDateTime.parse(eventParts[2], inputFormat);
            Task newTask = new Event(eventParts[0], fromDateTime, toDateTime);
            tasks.addTask(newTask);
            Storage.saveTasks(tasks.getTasks());
            printTaskAdded(newTask);
        } catch (DateTimeParseException e) {
            throw new ZennException("Invalid date format. Use: d/M/yyyy HHmm for both start and end times.");
        }
    }

    private static void filterTasksByDate(String dateInput) {
        try {
            LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("d/M/yyyy"));
            System.out.println("Tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            for (Task task : tasks.getTasks()) {
                if ((task instanceof Deadline && ((Deadline) task).isOnDate(date)) ||
                        (task instanceof Event && ((Event) task).isOnDate(date))) {
                    System.out.println(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Use: on d/M/yyyy (e.g., on 2/12/2019)");
        }
    }

    private static void printTaskAdded(Task task) {
        System.out.println("Ok, added liao:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the todo list. (stop procrastinating!)");
    }
}
