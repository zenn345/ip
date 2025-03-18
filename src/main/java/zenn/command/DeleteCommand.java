package zenn.command;

import zenn.task.Task;
import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.exceptions.ZennException;

/**
 * Represents a command that deletes a task from the task list.
 * It parses the arguments to determine the task index and removes the specified task.
 */
public class DeleteCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a DeleteCommand to delete a task from the task list.
     *
     * @param arguments The arguments passed from the user, which should be the task index.
     * @param tasks The TaskList object that holds the list of tasks.
     * @param storage The Storage object used for saving tasks after deletion.
     * @param ui The Ui object used for displaying messages to the user.
     */
    public DeleteCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the delete command. It parses the task index from the arguments,
     * removes the specified task, and updates the task list in storage.
     * If an invalid task index is provided, an error message is shown.
     */
    @Override
    public void execute() {
        try {
            if (arguments == null || arguments.trim().isEmpty()) {
                ui.showError("Please provide a valid task number.");
                return;
            }

            int taskIndex = Integer.parseInt(arguments.trim()) - 1;

            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                ui.showError("Invalid task index. Please enter a valid task number.");
                return;
            }

            Task taskToRemove = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            storage.saveTasks(tasks.getAllTasks());
            ui.showMessage("Got it. I've removed this task:");
            ui.showMessage(taskToRemove.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            ui.showError("Invalid task index format. Please enter a valid number.");
        } catch (ZennException e) {
            ui.showError("Task index out of range.");
        }
    }
}
