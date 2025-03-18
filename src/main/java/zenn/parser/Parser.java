package zenn.parser;

import zenn.command.Command;
import zenn.command.CommandFactory;

/**
 * Represents the command parser for the Zenn application.
 * This class is responsible for parsing user input, splitting the input into a command and its arguments,
 * and then using the CommandFactory to create the corresponding Command object.
 */
public class Parser {
    private final CommandFactory commandFactory;

    /**
     * Constructs a Parser object with the given CommandFactory.
     *
     * @param commandFactory The CommandFactory used to create commands based on user input.
     */
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
        return commandFactory.createCommand(command, arguments);
    }
}
