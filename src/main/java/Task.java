/**
 * Represents a task with a description and completion status.
 */
public class Task {
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
    
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

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


