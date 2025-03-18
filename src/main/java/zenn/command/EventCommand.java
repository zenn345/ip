package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.task.Event;
import zenn.exceptions.ZennException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public EventCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super("event");
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public void execute() {
        try {
            String[] parts = arguments.split(" /from | /to ");
            if (parts.length < 3) {
                ui.showError("Invalid event format. Please provide a description, start time, and end time.");
                ui.showMessage("The correct format for an event task is: 'description /from d/M/yyyy HHmm /to d/M/yyyy HHmm'");
                return;
            }

            String description = parts[0].trim();
            String fromDateTimeString = parts[1].trim();
            String toDateTimeString = parts[2].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeString, formatter);
            LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeString, formatter);

            Event event = new Event(description, fromDateTime, toDateTime);
            tasks.addTask(event);
            storage.saveTasks(tasks.getAllTasks());

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage(event.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            ui.showError("Error creating event task. Please check the format.");
        }
    }
}
