package zenn.storage;

import zenn.task.Deadline;
import zenn.task.Event;
import zenn.task.Task;
import zenn.task.Todo;
import zenn.exceptions.ZennException;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the storage system for tasks in the Zenn application.
 * This class is responsible for loading and saving tasks to a file.
 * It uses a predefined file path and provides methods to read and write task data.
 */
public class Storage {
    private static final String FILE_PATH = "./data/zenn.Zenn.txt";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Saves a list of tasks to the storage file.
     * Each task is written in a specific format to the file, one task per line.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     * The file is read line by line and tasks are recreated from the stored data.
     * If the file doesn't exist, an empty list is returned.
     *
     * @return A list of tasks loaded from the storage file.
     * @throws ZennException If there is an error while loading the tasks.
     */
    public static ArrayList<Task> loadTasks() throws ZennException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) return tasks;

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    System.out.println("Skipping corrupted line: " + line);
                    continue;
                }
                Task task;
                switch (parts[0]) {
                    case "T": task = new Todo(parts[2]);
                    break;
                    case "D":
                        LocalDateTime byDateTime = LocalDateTime.parse(parts[3], INPUT_FORMAT);
                        task = new Deadline(parts[2], byDateTime);
                        break;
                    case "E":
                        LocalDateTime fromDateTime = LocalDateTime.parse(parts[3], INPUT_FORMAT);
                        LocalDateTime toDateTime = LocalDateTime.parse(parts[4], INPUT_FORMAT);
                        task = new Event(parts[2], fromDateTime, toDateTime);
                        break;
                    default: System.out.println("Skipping unknown type: " + line);
                    continue;
                }
                if (parts[1].equals("X")) task.markAsDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new ZennException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
