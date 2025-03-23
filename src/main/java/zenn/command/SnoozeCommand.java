package zenn.command;

import zenn.storage.Storage;
import zenn.task.Deadline;
import zenn.task.Event;
import zenn.task.Task;
import zenn.task.TaskList;
import zenn.ui.Ui;

public class SnoozeCommand extends Command {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public SnoozeCommand(String arguments, TaskList taskList, Ui ui, Storage storage) {
        super(arguments);
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public String execute() {
        try {
            String[] parts = arguments.split(" ");
            int taskIndex = Integer.parseInt(parts[0]) - 1;
            int snoozeHours = Integer.parseInt(parts[1]);

            Task task = taskList.getTask(taskIndex);

            if (task instanceof Deadline) {
                ((Deadline) task).snooze(snoozeHours);
                storage.saveTasks(taskList.getAllTasks());
                return ui.showMessage("Task has been snoozed for " + snoozeHours
                    + " hours:\n" + taskList.getTask(taskIndex).toString());
            } else if (task instanceof Event) {
                ((Event) task).snooze(snoozeHours);
                storage.saveTasks(taskList.getAllTasks());
                return ui.showMessage("Task has been snoozed for " + snoozeHours
                    + " hours:\n" + taskList.getTask(taskIndex).toString());
            } else {
                return ui.showError("This task can't be snoozed.");
            }
        } catch (Exception e) {
            return ui.showError("Invalid snooze command.\n"
                + "Format: snooze <task_index> <hours>");
        }
    }
}
