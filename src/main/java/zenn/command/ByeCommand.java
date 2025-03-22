package zenn.command;

import zenn.ui.Ui;

/**
 * Represents a command that exits the application.
 * It displays a goodbye message and terminates the program.
 */
public class ByeCommand extends Command {
    private final Ui ui;

    /**
     * Constructs a ByeCommand to exit the application.
     *
     * @param arguments The arguments passed from the user (not used for this command).
     * @param ui The Ui object used for displaying messages to the user.
     */
    public ByeCommand(String arguments, Ui ui) {
        super(arguments);
        this.ui = ui;
    }

    /**
     * Executes the bye command. It shows a goodbye message to the user
     * and then terminates the program.
     */
    @Override
    public void execute() {
        ui.showMessage("Bye! See you again ah!");
        System.exit(0);
    }
}
