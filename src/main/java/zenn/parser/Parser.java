package zenn.parser;

import zenn.command.Command;
import zenn.command.CommandFactory;

public class Parser {
    private final CommandFactory commandFactory;

    public Parser(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public Command parseCommand(String userInput) {
        String[] commandParts = userInput.split(" ", 2);
        String command = commandParts[0];
        String arguments = (commandParts.length > 1) ? commandParts[1] : "";
        return commandFactory.createCommand(command, arguments);
    }
}
