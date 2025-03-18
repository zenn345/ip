package zenn.task;

import org.junit.jupiter.api.Test;
import zenn.task.Deadline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    @Test
    public void testIsOnDate_sameDate_returnsTrue() {
        LocalDateTime deadlineTime = LocalDateTime.parse("15/03/2024 1800", INPUT_FORMAT);
        Deadline deadline = new Deadline("Submit assignment", deadlineTime);
        assertTrue(deadline.isOnDate(LocalDate.of(2024,3,15)));
    }

    @Test
    public void testIsOnDate_differentDate_returnsFalse() {
        LocalDateTime deadlineTime = LocalDateTime.parse("15/03/2024 1800", INPUT_FORMAT);
        Deadline deadline = new Deadline("Submit assignment", deadlineTime);
        assertFalse(deadline.isOnDate(LocalDate.of(2024,3,16)));
    }

    @Test
    public void testToFileFormat_correctFormat() {
        LocalDateTime deadlineTime = LocalDateTime.parse("15/03/2024 1800", INPUT_FORMAT);
        Deadline deadline = new Deadline("Submit assignment", deadlineTime);
        String expected = "D |   | Submit assignment | 15/03/2024 1800";
        assertEquals(expected, deadline.toFileFormat());
    }

    @Test
    public void testToString_correctFormat() {
        LocalDateTime deadlineTime = LocalDateTime.parse("15/03/2024 1800", INPUT_FORMAT);
        Deadline deadline = new Deadline("Submit assignment", deadlineTime);
        String expected = "[D][ ]Submit assignment (by: Mar 15 2024, 6:00 pm)";
        assertEquals(expected, deadline.toString());
    }
}
