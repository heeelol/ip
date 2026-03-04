package yoru;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }

    public Task mark(int index) throws YoruException {
        validateIndex(index);
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmark(int index) throws YoruException {
        validateIndex(index);
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    public Task addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    public Task addDeadline(String description, String by) throws YoruException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        return task;
    }

    public Task addEvent(String description, String from, String to) throws YoruException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        return task;
    }

    public Task delete(int index) throws YoruException {
        validateIndex(index);
        return tasks.remove(index);
    }

    private void validateIndex(int index) throws YoruException {
        if (index < 0 || index >= tasks.size()) {
            throw new YoruException("Invalid task number.");
        }
    }
}