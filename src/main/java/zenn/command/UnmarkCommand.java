package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.exceptions.ZennException;

/**
 * Represents a command that marks a task as incomplete.
 * It parses the task index and marks the task at the given index as not done.
 */
public class UnmarkCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a UnmarkCommand with the specified arguments, task list,
     * storage and user interface.
     *
     * @param arguments The arguments passed from the user, which should be the task index.
     * @param tasks The TaskList object that holds the list of tasks.
     * @param storage The Storage object used for saving tasks.
     * @param ui The Ui object used for displaying messages to the user.
     */
    public UnmarkCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the command to mark the task at the specified index as not yet done.
     * If the task index is invalid or the format is incorrect, an error message is displayed.
     */
    @Override
    public void execute() {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            tasks.unmarkTaskAsDone(taskIndex);
            storage.saveTasks(tasks.getAllTasks());
            ui.showMessage("OMG... More work?");
            ui.showMessage(tasks.getTask(taskIndex).toString());
        } catch (NumberFormatException e) {
            ui.showError("Invalid task index format. Please enter a valid number.");
        } catch (ZennException e) {
            ui.showError("Task index out of range.");
        }
    }
}
