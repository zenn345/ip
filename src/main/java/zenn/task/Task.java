package zenn.task;

/**
 * Represents a generic task with a description, completion status, and a task type.
 * Subclasses of Task will represent specific task types, such as Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a Task object with a description and a task type.
     * The task is initially not marked as done.
     *
     * @param description A brief description of the task.
     * @param type The type of the task (e.g., Todo, Deadline, Event).
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns a string representing the task's status icon.
     * The status icon is "X" if the task is done, otherwise it's a space.
     *
     * @return The status icon ("X" for done, space for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     * The task's status will be set to done, and the status icon will be updated.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     * The task's status will be set to incomplete, and the status icon will be updated.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a string format suitable for saving to a file.
     * This method must be implemented by subclasses to return the task's file format.
     *
     * @return A string representing the task in file format.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task for display to the user.
     * The string includes the status icon and the task description.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }
}