package zenn.command;

import zenn.ui.Ui;

public class UnknownCommand extends Command {
    private final Ui ui;

    public UnknownCommand(Ui ui) {
        super("unknown");
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.showError("Unknown command! Please enter a valid command.");
    }
}
