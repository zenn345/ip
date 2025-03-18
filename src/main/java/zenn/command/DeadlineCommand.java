package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.task.Deadline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command that adds a deadline task to the task list.
 * It parses the arguments to get the description and deadline date,
 * and creates a new deadline task which is then added to the task list.
 */
public class DeadlineCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a DeadlineCommand to add a deadline task to the task list.
     *
     * @param arguments The arguments passed from the user, which should include description and deadline date.
     * @param tasks The TaskList object that holds the list of tasks.
     * @param storage The Storage object used for saving tasks.
     * @param ui The Ui object used for displaying messages to the user.
     */
    public DeadlineCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the deadline command. It parses the deadline task arguments, creates a new Deadline task,
     * and adds it to the task list. If the format is incorrect, an error message is shown.
     */
    @Override
    public void execute() {
        try {
            String[] parts = arguments.split(" /by ");
            if (parts.length < 2) {
                ui.showError("Invalid deadline format. Please provide a description and a deadline date.");
                ui.showMessage("The correct format for a deadline task is: 'description /by d/M/yyyy HHmm'");
                return;
            }
            String description = parts[0].trim();
            String dateTimeString = parts[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime byDateTime = LocalDateTime.parse(dateTimeString, formatter);

            Deadline deadline = new Deadline(description, byDateTime);
            tasks.addTask(deadline);
            storage.saveTasks(tasks.getAllTasks());

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage(deadline.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            ui.showError("Error creating deadline task. Please check the format.");
        }
    }
}
