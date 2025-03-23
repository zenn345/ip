package zenn.command;

import zenn.task.TaskList;
import zenn.ui.Ui;

import java.lang.StringBuilder;

/**
 * Represents a command that lists all the tasks in the task list.
 * It displays each task's index and description.
 */
public class ListCommand extends Command {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a ListCommand to list the tasks from the task list.
     *
     * @param arguments The arguments passed from the user.
     * @param tasks The TaskList object that holds the list of tasks.
     * @param ui The Ui object used for displaying messages to the user.
     */
    public ListCommand(String arguments, TaskList tasks, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the list command. It displays all tasks in the task list.
     * If there are no tasks present, a message is shown indication there
     * are no tasks. If there is an error, an error message is shown.
     *
     * @return A message to show all the tasks or an error message.
     */
    @Override
    public String execute() {
        if (tasks.size() == 0) {
            return ui.showMessage("Nothing to do yet!");
        }

        StringBuilder result = new StringBuilder();
        result.append(ui.showMessage("Here are your tasks:\n"));
        for (int i = 0; i < tasks.size(); i++) {
            try {
                result.append((i + 1) + ". " + tasks.getTask(i).toString() + "\n");
            } catch (IllegalArgumentException e) {
                return ui.showError("Error retrieving task as index " + (i + 1) + ": " + e.getMessage());
            }
        }
        return result.toString();
    }
}