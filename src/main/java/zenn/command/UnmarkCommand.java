package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.exceptions.ZennException;

public class UnmarkCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public UnmarkCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

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
