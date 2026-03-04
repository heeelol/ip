package yoru;

/**
 * Represents a basic todo task without date or time constraints.
 */
public class Todo extends Task {

    /**
     * Creates a todo task.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted display string for this todo task.
     *
     * @return Display-formatted todo task string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts this todo task into the storage file format.
     *
     * @return Serialized todo task string.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
