package zenn.command;

/**
 * Represents an abstract command in the application.
 * Commands are used to perform specific actions in the application
 * based on user input. Each command has associated arguments
 * and must implement the {@code execute} method to define the action.
 */
public abstract class Command {
    protected String arguments;

    /**
     * Constructs a Command with the specified arguments.
     *
     * @param arguments The arguments passed by the user when executing the command.
     */
    public Command(String arguments) {
        this.arguments = arguments;
    }

    /**
     * This method is abstract and serves as a blueprint for all command classes.
     *
     * @return The result of executing the command, typically a message or status.
     */
    public abstract String execute();
}

