import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws ZennException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZennException("Invalid task index!");
        }
        return tasks.remove(index);
    }

    public Task getTask(int index) throws ZennException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZennException("Invalid task index!");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public void markTaskAsDone(int index) throws ZennException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZennException("Invalid task index!");
        }
        tasks.get(index).markAsDone();
    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Yay, nothing to do!";
        }

        StringBuilder taskListString = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskListString.append(i + 1).append(". ").append(task).append("\n");
        }
        return taskListString.toString();
    }
}
