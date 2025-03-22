package zenn.parser;

import zenn.command.Command;
import zenn.command.CommandFactory;
import zenn.ui.Ui;
import zenn.task.TaskList;

public class Parser {
    private final CommandFactory commandFactory;
    private final Ui ui;
    private final TaskList tasks;

    public Parser(CommandFactory commandFactory, Ui ui, TaskList tasks) {
        this.commandFactory = commandFactory;
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Parses the user input to extract the command and its arguments,
     * and returns the corresponding Command object.
     *
     * @param userInput The input string from the user, which consists of the command and optional arguments.
     * @return A Command object that corresponds to the parsed user input.
     */
    public Command parseCommand(String userInput) {
        String[] commandParts = userInput.split(" ", 2);
        String command = commandParts[0];
        String arguments = (commandParts.length > 1) ? commandParts[1] : "";
        return commandFactory.createCommand(command, arguments);
    }
}
