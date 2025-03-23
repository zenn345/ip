package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.task.Event;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command that adds an event task to the task list.
 * It parses the arguments to get the description and event date,
 * and creates a new event task which is then added to the task list.
 */
public class EventCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a EventCommand to add an event task to the task list.
     *
     * @param arguments The arguments passed from the user, which should include description and event date.
     * @param tasks The TaskList object that holds the list of tasks.
     * @param storage The Storage object used for saving tasks.
     * @param ui The Ui object used for displaying messages to the user.
     */
    public EventCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the event command. It parses the event task arguments, creates a new Event task,
     * and adds it to the task list. If the format is incorrect, an error message is shown.
     *
     * @return A message showing that Event task has been created or an error message.
     */
    @Override
    public String execute() {
        try {
            String[] parts = arguments.split(" /from | /to ");
            if (parts.length != 3) {
                return ui.showError("Invalid event format. Please provide a description, start time, and end time.\n"
                    + "The correct format for an event task is:\n"
                    + "event [description] /from d/M/yyyy HHmm /to d/M/yyyy HHmm'");
            }

            String description = parts[0].trim();
            String fromDateTimeString = parts[1].trim();
            String toDateTimeString = parts[2].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeString, formatter);
            LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeString, formatter);

            if (toDateTime.isBefore(LocalDateTime.now()) ||
                fromDateTime.isBefore(LocalDateTime.now()) ||
                fromDateTime.isAfter(toDateTime)) {
                return ui.showError("Start time and end time cannot be in the past...");
            }

            Event event = new Event(description, fromDateTime, toDateTime);
            tasks.addTask(event);
            storage.saveTasks(tasks.getAllTasks());

            return "Got it. I've added this task:\n"
                + event.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (Exception e) {
            return ui.showError("Error creating event task. Please check the format.");
        }
    }
}