package zenn.command;

import zenn.ui.Ui;

/**
 * Represents all unknown command that is not specified.
 * It displays an error message to inform users that the entered
 * command is invalid.
 */
public class UnknownCommand extends Command {
    private final Ui ui;

    /**
     * Constructs a UnknownCommand
     *
     * @param ui The Ui object used for displaying messages to the user.
     */
    public UnknownCommand(Ui ui) {
        super("unknown");
        this.ui = ui;
    }

    /**
     * Executes the unknown command. It shows an error message to the user.
     */
    @Override
    public void execute() {
        ui.showError("Unknown command! Please enter a valid command.");
    }
}
