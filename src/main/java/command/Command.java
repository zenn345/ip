package command;

public class Command {
    protected String command;
    protected String arguments;

    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }
}
