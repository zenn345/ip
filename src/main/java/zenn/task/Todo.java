package zenn.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
