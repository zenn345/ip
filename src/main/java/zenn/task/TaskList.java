package zenn.task;

import zenn.exceptions.ZennException;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manage and retrieve tasks.
 * The TaskList class allows adding, removing, and retrieving tasks by index, as well as checking the list size.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the provided list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     * @throws IllegalArgumentException If the index is invalid.
     */
    public Task removeTask(int index) throws NumberFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task index!");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IllegalArgumentException If the index is invalid (out of bounds).
     */
    public Task getTask(int index) throws NumberFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task index!");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the TaskList.
     *
     * @return An ArrayList of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @throws ZennException If the index is invalid (out of bounds).
     */
    public void markTaskAsDone(int index) throws ZennException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZennException("Invalid task index!");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the task at the specified index as done.
     *
     * @param index The index of the task to unmark.
     * @throws ZennException If the index is invalid (out of bounds).
     */
    public void unmarkTaskAsDone(int index) throws ZennException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZennException("Invalid task index!");
        }
        tasks.get(index).unmarkAsDone();
    }

    /**
     * Retrieves tasks with matching keyword from the TaskList.
     *
     * @param keyword The word to search for.
     * @return The list of tasks with matching keyword.
     */
    public String findTasks(String keyword) {
        StringBuilder matchingTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.append(i + 1).append(". ").append(task).append("\n");
            }
        }

        if (matchingTasks.length() == 0) {
            return "No tasks found matching the keyword: " + keyword;
        }
        return "Here are the matching tasks in your list:\n" + matchingTasks.toString();
    }

    /**
     * Returns a string representation of all tasks in the TaskList.
     *
     * @return A string listing all tasks or a message if the list is empty.
     */
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