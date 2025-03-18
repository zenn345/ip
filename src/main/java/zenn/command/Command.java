package zenn.command;

public abstract class Command {
    //protected String command;
    protected String arguments;

    public Command(String arguments) {
        //this.command = command;
        this.arguments = arguments;
    }

    public abstract void execute();

    //public String getCommand() { return command; }

    //public String getArguments() { return arguments;}
}
