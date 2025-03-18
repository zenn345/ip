package zenn.command;

import zenn.Zenn;
import zenn.exceptions.ZennException;
import zenn.task.TaskList;
import zenn.ui.Ui;

public class ListCommand extends Command {
    private final TaskList tasks;
    private final Ui ui;

    public ListCommand(String arguments, TaskList tasks, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute() {
        if (tasks.size() == 0) {
            ui.showMessage("Nothing to do yet!");
        }

        ui.showMessage("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            try {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i).toString());
            } catch (ZennException e) {
                ui.showError("Error retrieving task as index " + (i + 1) + ": " + e.getMessage());
            }
        }
    }
}
