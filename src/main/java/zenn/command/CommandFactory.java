package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;

public class CommandFactory {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public CommandFactory(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

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
