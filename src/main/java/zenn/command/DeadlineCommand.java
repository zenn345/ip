package zenn.command;

import zenn.task.TaskList;
import zenn.storage.Storage;
import zenn.ui.Ui;
import zenn.task.Deadline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public DeadlineCommand(String arguments, TaskList tasks, Storage storage, Ui ui) {
        super(arguments);
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public void execute() {
        try {
            String[] parts = arguments.split(" /by ");
            if (parts.length < 2) {
                ui.showError("Invalid deadline format. Please provide a description and a deadline date.");
                ui.showMessage("The correct format for a deadline task is: 'deadline description /by d/M/yyyy HHmm'");
                return;
            }
            String description = parts[0].trim();
            String dateTimeString = parts[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime byDateTime = LocalDateTime.parse(dateTimeString, formatter);

            Deadline deadline = new Deadline(description, byDateTime);
            tasks.addTask(deadline);
            storage.saveTasks(tasks.getAllTasks());

            StringBuilder response = new StringBuilder("Got it. I've added this task:\n");
            StringBuilder Deadlineresponse = new StringBuilder(deadline.toString());
            StringBuilder UpdateTaskresponse = new StringBuilder("Now you have " + tasks.size() + " tasks in the list.");
            response.append(Deadlineresponse).append(UpdateTaskresponse);
            //ui.showMessage("Got it. I've added this task:");
            //ui.showMessage(deadline.toString());
            //ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showMessage(response.toString());
        } catch (Exception e) {
            ui.showError("Error creating deadline task. Please check the format.");
        }
    }
}


