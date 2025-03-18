package zenn.command;

import zenn.task.Task;
import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.exceptions.ZennException;

public class DeleteCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public DeleteCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

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
