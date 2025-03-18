package zenn.task;

import zenn.task.Task;
import zenn.task.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, which includes a description and a due date.
 * A deadline task has a specific time and date by which it must be completed.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Constructs a Deadline object with a description and a due date.
     *
     * @param description A brief description of the deadline task.
     * @param by The LocalDateTime representing the deadline's due date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Checks if the deadline task is on a specific date.
     *
     * @param date The date to check against the deadline.
     * @return True if the deadline is on the specified date, false otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        return this.by.toLocalDate().equals(date);
    }

    //public String formatDate(DateTimeFormatter formatter) {
    //    return by.format(formatter);
    //}

    /**
     * Converts the deadline task into a string format suitable for saving to a file.
     *
     * @return The string representation of the deadline task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + by.format(FILE_FORMAT);
    }

    /**
     * Returns a string representation of the deadline task for display to the user.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
