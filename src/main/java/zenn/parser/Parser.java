package zenn.parser;

import zenn.command.Command;
import zenn.command.FindCommand;
import zenn.command.CommandFactory;

public class Parser {
    private final CommandFactory commandFactory;

    public Parser(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
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

        if (command.equals("find")) {
            if (arguments.isEmpty()) {
                return new FindCommand(ui, taskList, "Missing Keyword");
            }
            return new FindCommand(ui, taskList, arguments);
        }

        return commandFactory.createCommand(command, arguments);
    }
}
