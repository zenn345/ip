package zenn.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task, which includes a description, a start date and end date.
 * An event task has a specific duration in which the event takes place.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs an Event object with a description, a start date and end date.
     *
     * @param description A brief description of the event.
     * @param from The LocalDateTime representing the event's start date and time.
     * @param to The LocalDateTime representing the event's end date and time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if the event task is on a specific duration.
     *
     * @param date The date to check against the duration.
     * @return True if the duration is on the specified date, false otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        return this.from.toLocalDate().equals(date) || this.to.toLocalDate().equals(date);
    }

    /**
     * Converts the event task into a string format suitable for saving to a file.
     *
     * @return The string representation of the event task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | "
            + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT);
    }

    /**
     * Returns a string representation of the event task for display to the user.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}