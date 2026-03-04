package yoru;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with start and end date-time values.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an event task.
     *
     * @param description Task description.
     * @param from Start date-time in yyyy-MM-dd HHmm format.
     * @param to End date-time in yyyy-MM-dd HHmm format.
     * @throws YoruException If parsing fails or end is before start.
     */
    public Event(String description, String from, String to) throws YoruException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from.trim(), INPUT_FORMAT);
            this.to = LocalDateTime.parse(to.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new YoruException("Event datetime format: yyyy-MM-dd HHmm (e.g., 2019-10-15 1800)");
        }

        if (this.to.isBefore(this.from)) {
            throw new YoruException("Event end time must be after start time.");
        }
    }

    /**
     * Returns a formatted display string for this event task.
     *
     * @return Display-formatted event task string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMAT)
                + " to: " + to.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Converts this event task into the storage file format.
     *
     * @return Serialized event task string.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from.format(INPUT_FORMAT)
                + " | " + to.format(INPUT_FORMAT);
    }
}
