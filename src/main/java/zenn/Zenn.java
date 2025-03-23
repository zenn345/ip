package zenn;

import zenn.command.Command;
import zenn.command.CommandFactory;
import zenn.exceptions.ZennException;
import zenn.parser.Parser;
import zenn.storage.Storage;
import zenn.task.*;
import zenn.ui.Ui;

/**
 * The main entry point of the Zenn task management application.
 * Initializes the storage, task list, and user interface components,
 * and runs the main command loop, allowing users to interact with the application.
 */
public class Zenn {

    private static TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private final CommandFactory commandFactory;

    /**
     * Initializes the Zenn application with necessary components:
     * UI, storage, task list, command factory, and parser.
     * If loading tasks from storage fails, a new task list is created.
     */
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
        this.parser = new Parser(commandFactory, ui, tasks);
    }

    /**
     * Runs the main command loop of the Zenn application.
     * It continuously reads user input, parses the command, and executes it until the user quits the application.
     */
    public String run() {
        System.out.println(ui.showWelcome());
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
                System.out.println(ui.showError("Unexpected error. Please try again."));
            }
        }
        return ui.showGoodbye();
    }

    /**
     * Processes user input and returns a response (for GUI integration).
     *
     * @param input The user input.
     * @return The response message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            return command.execute();
        } catch (Exception e) {
            return ui.showError("Unexpected Error. Please try again.");
        }
    }

    /**
     * Main method to launch the Zenn application.
     *
     * @param args Command line arguments (unused in this application).
     */
    public static void main(String[] args) {
        new Zenn().run();
    }
}