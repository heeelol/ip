package yoru;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private final LocalDateTime from;
    private final LocalDateTime to;

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMAT)
                + " to: " + to.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from.format(INPUT_FORMAT)
                + " | " + to.format(INPUT_FORMAT);
    }
}
