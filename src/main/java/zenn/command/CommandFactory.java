package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;

/**
 * Creates the appropriate command based on the type of command specified by the user.
 * It helps decouple the command creation logic from the main program flow.
 */
public class CommandFactory {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a CommandFactory with the given task list, storage, and user interface.
     *
     * @param tasks The task list used to manage tasks.
     * @param storage The storage used to save and load tasks.
     * @param ui The user interface used to interact with the user.
     */
    public CommandFactory(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Creates a command based on the provided command type and arguments.
     *
     * @param commandType The type of command to create (e.g., "done", "todo", "deadline").
     * @param arguments The arguments passed with the command.
     * @return A new {@code Command} object corresponding to the specified command type.
     */
    public Command createCommand(String commandType, String arguments) {
        switch (commandType) {
            case "done":
                return new DoneCommand(arguments, tasks, storage, ui);
            case "unmark":
                return new UnmarkCommand(arguments, tasks, storage, ui);
            case "todo":
                return new TodoCommand(arguments, tasks, storage, ui);
            case "deadline":
                return new DeadlineCommand(arguments, tasks, storage, ui);
            case "event":
                return new EventCommand(arguments, tasks, storage, ui);
            case "list":
                return new ListCommand(arguments, tasks, ui);
            case "bye":
                return new ByeCommand(arguments, ui);
            case "delete":
                return new DeleteCommand(arguments, tasks, storage, ui);
            default:
                return new UnknownCommand(ui);
        }
    }
}