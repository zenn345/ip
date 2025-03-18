package zenn;

import zenn.command.Command;
import zenn.command.CommandFactory;
import zenn.exceptions.ZennException;
import zenn.parser.Parser;
import zenn.storage.Storage;
import zenn.task.*;
import zenn.ui.Ui;

public class Zenn {

    private static TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private final CommandFactory commandFactory;

    public Zenn() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (ZennException e) {
            ui.showError("Error loading tasks from storage.");
            tasks = new TaskList();
        }
        this.commandFactory = new CommandFactory(tasks, storage, ui);
        this.parser = new Parser(commandFactory);
    }

    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parseCommand(userInput);
                command.execute();
                if (userInput.equalsIgnoreCase("bye")) {
                    isRunning = false;
                }
            } catch (Exception e) {
                ui.showError("Unexpected error. Please try again.");
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Zenn().run();
    }
}
