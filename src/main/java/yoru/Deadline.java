package yoru;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a single due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private final LocalDate by;

    /**
     * Creates a deadline task.
     *
     * @param description Task description.
     * @param by Due date in yyyy-MM-dd format.
     * @throws YoruException If the date format is invalid.
     */
    public Deadline(String description, String by) throws YoruException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new YoruException("Deadline date format: yyyy-MM-dd (e.g., 2019-10-15)");
        }
    }

    /**
     * Returns a formatted display string for this deadline task.
     *
     * @return Display-formatted deadline task string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Converts this deadline task into the storage file format.
     *
     * @return Serialized deadline task string.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by.format(INPUT_FORMAT);
    }
}
