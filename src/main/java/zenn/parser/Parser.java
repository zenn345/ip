package zenn.parser;

import zenn.command.Command;

import command.Command;

public class Parser {
    public Command parseCommand(String userInput) {
        String[] commandParts = userInput.split(" ", 2);
        String command = commandParts[0];
        String arguments = (commandParts.length > 1) ? commandParts[1] : "";
        return new Command(command, arguments);
    }
}
