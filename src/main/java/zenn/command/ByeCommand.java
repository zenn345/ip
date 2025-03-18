package zenn.command;

import zenn.ui.Ui;

public class ByeCommand extends Command {
    private final Ui ui;

    public ByeCommand(String arguments, Ui ui) {
        super(arguments);
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.showMessage("Bye! See you again ah!");
        System.exit(0);
    }
}
