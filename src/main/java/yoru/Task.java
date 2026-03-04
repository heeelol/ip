package yoru;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks whether the task description contains the given keyword.
     * Matching is case-insensitive.
     *
     * @param keyword Keyword to search for.
     * @return True if the keyword appears in the description.
     */
    public boolean containsKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Converts the task into the storage file format.
     *
     * @return A serialized representation of the task.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task.
     *
     * @return The task status and description in the format "[X] description" or "[ ] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}


