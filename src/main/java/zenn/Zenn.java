package zenn;

import command.Command;
import exceptions.ZennException;
import parser.Parser;
import storage.Storage;
import task.*;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Zenn {

    private static TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Zenn() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (ZennException e) {
            ui.showError("Error loading tasks from storage.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            Parser parser = new Parser();
            Command command = parser.parseCommand(userInput);
            isRunning = executeCommand(command);
        }
        ui.showGoodbye();
    }

    private boolean executeCommand(Command command) {
        switch (command.getCommand()) {
            case "list":
                ui.showMessage(tasks.listTasks());
                break;
            case "done":
                try {
                    int taskIndex = Integer.parseInt(command.getArguments()) - 1;
                    tasks.markTaskAsDone(taskIndex);
                    storage.saveTasks(tasks.getAllTasks());
                } catch (NumberFormatException e) {
                    ui.showError("Invalid task index format. Please enter a valid number.");
                } catch (ZennException e) {
                    ui.showError("task.Task index out of range.");
                }
                break;
            case "todo":
                tasks.addTask(new Todo(command.getArguments()));
                storage.saveTasks(tasks.getAllTasks());
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage(tasks.getAllTasks().get(tasks.size() - 1).toString());
                ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "deadline":
                try {
                    String[] parts = command.getArguments().split(" /by ");
                    if (parts.length < 2) {
                        ui.showError("Invalid deadline format. Please provide a description and a deadline date.");
                        ui.showMessage("The correct format for a deadline task is: 'description /by d/M/yyyy HHmm'");
                        break;
                    }
                    String description = parts[0].trim();
                    String dateTimeString = parts[1].trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime byDateTime = LocalDateTime.parse(dateTimeString, formatter);

                    Deadline deadline = new Deadline(description, byDateTime);
                    tasks.addTask(deadline);
                    storage.saveTasks(tasks.getAllTasks());

                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(deadline.toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    ui.showError("Error creating deadline task. Please check the format.");
                }
                break;
            case "event":
                try {
                    String[] parts = command.getArguments().split(" /from | /to ");
                    if (parts.length < 3) {
                        ui.showError("Invalid event format. Please provide a description, start time, and end time.");
                        ui.showMessage("The correct format for an event task is: 'description /from d/M/yyyy HHmm /to d/M/yyyy HHmm'");
                        break;
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
                break;
            case "delete":
                try {
                    int taskIndex = Integer.parseInt(command.getArguments()) - 1;

                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        ui.showError("Invalid task index. Please enter a valid task number.");
                        break;
                    }

                    Task taskToRemove = tasks.getTask(taskIndex);
                    tasks.removeTask(taskIndex);
                    storage.saveTasks(tasks.getAllTasks());

                    ui.showMessage("Got it. I've removed this task:");
                    ui.showMessage(taskToRemove.toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                } catch (NumberFormatException e) {
                    ui.showError("Invalid task index format. Please enter a valid number.");
                } catch (ZennException e) {
                    ui.showError("task.Task index out of range.");
                }
                break;
            case "bye":
                return false;
            default:
                ui.showError("Unknown command: " + command.getCommand());
        }
        return true;
    }

    public static void main(String[] args) {
        new Zenn().run();
    }
}
