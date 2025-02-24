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

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
