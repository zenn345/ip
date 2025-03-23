package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.task.Todo;

/**
 * Represents a command that adds a task to the task list.
 * It parses the arguments to get the description and creates
 * a new task which is then added to the task list.
 */
public class TodoCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a TodoCommand to add a task to the task list.
     *
     * @param arguments The arguments passed from the user, which should include description.
     * @param tasks     The TaskList object that holds the list of tasks.
     * @param storage   The Storage object used for saving tasks.
     * @param ui        The Ui object used for displaying messages to the user.
     */
    public TodoCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the todo command. It parses the task arguments,
     * creates a new Todo task, and adds it to the task list.
     *
     * @return A list of task including the new task added.
     */
    @Override
    public String execute() {
        if (arguments.isEmpty()) {
            return "Description cannot be empty";
        } else {
            tasks.addTask(new Todo(arguments));
            storage.saveTasks(tasks.getAllTasks());
            String message = "Got it. I've added this task:\n"
                + tasks.getAllTasks().get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
            return message;
        }
    }
}
