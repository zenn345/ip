package zenn.task;

import org.junit.jupiter.api.Test;
import zenn.task.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
public class EventTest {

    @Test
    void testIsOnDate_TrueForMatchingDate() {
        LocalDateTime from = LocalDateTime.of(2025, 3, 18, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 3, 18, 12, 0);
        Event event = new Event("Event", from, to);
        LocalDate date = LocalDate.of(2025, 3, 18);

        boolean result = event.isOnDate(date);
        assertTrue(result, "Event should be on specified date.");
    }

    @Test
    void testIsOnDate_FalseForNonMatchingDate() {
        LocalDateTime from = LocalDateTime.of(2025, 3, 18, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 3, 18, 12, 0);
        Event event = new Event("Event", from, to);
        LocalDate date = LocalDate.of(2025, 3, 19);

        boolean result = event.isOnDate(date);
        assertFalse(result, "Event should not be on the specified date.");
    }

    @Test
    void testToFileFormat() {
        LocalDateTime from = LocalDateTime.of(2025, 3, 18, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 3, 18, 12, 0);
        Event event = new Event("Event", from, to);

        String expectedFileFormat = "E |   | Event | 18/3/2025 1000 | 18/3/2025 1200";
        String result = event.toFileFormat();
        assertEquals(expectedFileFormat, result, "File format should match the expected format.");
    }

    @Test
    void testToString() {
        LocalDateTime from = LocalDateTime.of(2025, 3, 18, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 3, 18, 12, 0);
        Event event = new Event("Event", from, to);

        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        String expectedString = "[E][ ]Event (from: Mar 18 2025, 10:00 am to: Mar 18 2025, 12:00 pm)";
        String result = event.toString();
        assertEquals(expectedString, result, "String format should match the expected format.");
    }
}

