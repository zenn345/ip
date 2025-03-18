package zenn.command;

import zenn.task.TaskList;
import zenn.ui.Ui;

/**
 * Represents a command that allows the user to find tasks in the task list based on a given keyword.
 * The command searches through task descriptions and displays the tasks that contain the keyword.
 */
public class FindCommand extends Command {
    private final Ui ui;
    private final TaskList taskList;
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified UI, task list, and search keyword.
     *
     * @param ui the UI object to handle user interaction and display results.
     * @param taskList the task list that contains the tasks to search through.
     * @param keyword the keyword to search for in the task descriptions.
     */
    public FindCommand(Ui ui, TaskList taskList, String keyword) {
        super("find");
        this.ui = ui;
        this.taskList = taskList;
        this.keyword = keyword;
    }

    /**
     * Executes the find command, which searches for tasks in the task list based on the keyword.
     * Displays the matching tasks to the user via the UI.
     */
    @Override
    public void execute() {
        if ("Missing keyword".equals(keyword)) {
            ui.showError("Missing keyword. Please specify a keyword.");
        } else {
            String result = taskList.findTasks(keyword);
            ui.showMessage(result);
        }
    }
}
