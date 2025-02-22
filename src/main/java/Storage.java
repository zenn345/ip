import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Storage {
    private static final String FILE_PATH = "./data/Zenn.txt";

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

    public static ArrayList<Task> loadTasks() {
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
                    case "D": task = new Deadline(parts[2], parts[3]);
                    break;
                    case "E": task = new Event(parts[2], parts[3], parts[4]);
                    break;
                    default: System.out.println("Skipping unknown type: " + line);
                    continue;
                }
                if (parts[1].equals("X")) task.markAsDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
