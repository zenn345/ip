package zenn.task;

import org.junit.jupiter.api.Test;
import zenn.task.Todo;

import static org.junit.jupiter.api.Assertions.*;
public class TodoTest {

    @Test
    void testToFileFormat() {
        Todo todo = new Todo("Finish homework");
        String expectedFileFormat = "T |   | Finish homework";
        String result = todo.toFileFormat();
        assertEquals(expectedFileFormat, result);
    }

    @Test
    void testToString() {
        Todo todo = new Todo("Finish homework");
        String expectedString = "[T][ ]Finish homework";
        String result = todo.toString();
        assertEquals(expectedString, result);
    }

    @Test
    void testToFileFormat_WhenDone() {
        Todo todo = new Todo("Complete assignment");
        todo.markAsDone();
        String expectedFileFormat = "T | X | Complete assignment";
        String result = todo.toFileFormat();
        assertEquals(expectedFileFormat, result);
    }

    @Test
    void testToString_WhenDone() {
        Todo todo = new Todo("Complete assignment");
        todo.markAsDone();
        String expectedString = "[T][X]Complete assignment";
        String result = todo.toString();
        assertEquals(expectedString, result);
    }
}
