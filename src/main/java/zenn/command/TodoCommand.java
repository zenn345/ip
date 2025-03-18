package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.task.Todo;

public class TodoCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public TodoCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public void execute() {
        tasks.addTask(new Todo(arguments));
        storage.saveTasks(tasks.getAllTasks());
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(tasks.getAllTasks().get(tasks.size() - 1).toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

}
