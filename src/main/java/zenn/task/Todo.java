package zenn.task;

/**
 * Represents a Todo task in the application.
 * A Todo task is a task that does not have a specific deadline or event time.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Converts the Todo task to a format suitable for saving to a file.
     * The file format includes the task type, completion status, and description.
     *
     * @return A string representing the Todo task in the file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }

    /**
     * Returns a string representation of the Todo task.
     * The string format includes the task type and description, along with the completion status.
     *
     * @return A string representing the Todo task in a human-readable format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
