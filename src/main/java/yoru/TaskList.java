package yoru;

import java.util.ArrayList;

/**
 * Represents the in-memory list of tasks and operations on them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list from an existing collection.
     *
     * @param tasks Tasks to initialize the list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return Task count.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Zero-based task index.
     * @return The task at the index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns all tasks.
     *
     * @return Mutable task list reference.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param index Zero-based task index.
     * @return Updated task.
     * @throws YoruException If the index is invalid.
     */
    public Task mark(int index) throws YoruException {
        validateIndex(index);
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done.
     *
     * @param index Zero-based task index.
     * @return Updated task.
     * @throws YoruException If the index is invalid.
     */
    public Task unmark(int index) throws YoruException {
        validateIndex(index);
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Adds a todo task.
     *
     * @param description Todo description.
     * @return The created task.
     */
    public Task addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a deadline task.
     *
     * @param description Deadline description.
     * @param by Deadline date string.
     * @return The created task.
     * @throws YoruException If the date format is invalid.
     */
    public Task addDeadline(String description, String by) throws YoruException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        return task;
    }

    /**
     * Adds an event task.
     *
     * @param description Event description.
     * @param from Event start date-time string.
     * @param to Event end date-time string.
     * @return The created task.
     * @throws YoruException If date-time values are invalid.
     */
    public Task addEvent(String description, String from, String to) throws YoruException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task.
     *
     * @param index Zero-based task index.
     * @return The removed task.
     * @throws YoruException If the index is invalid.
     */
    public Task delete(int index) throws YoruException {
        validateIndex(index);
        return tasks.remove(index);
    }

    /**
     * Finds tasks whose descriptions contain the keyword.
     *
     * @param keyword Keyword to search for.
     * @return Matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Ensures the given task index points to an existing task.
     *
     * @param index Zero-based task index.
     * @throws YoruException If the index is out of bounds.
     */
    private void validateIndex(int index) throws YoruException {
        if (index < 0 || index >= tasks.size()) {
            throw new YoruException("Invalid task number.");
        }
    }
}