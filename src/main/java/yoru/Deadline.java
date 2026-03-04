package yoru;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private final LocalDate by;

    public Deadline(String description, String by) throws YoruException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new YoruException("Deadline date format: yyyy-MM-dd (e.g., 2019-10-15)");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by.format(INPUT_FORMAT);
    }
}
