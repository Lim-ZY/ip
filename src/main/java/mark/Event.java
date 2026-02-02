package mark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs over a specific duration.
 */
public class Event extends Task {
    /**
     * Input format of date and time
     **/
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    /**
     * Returns Event object for the Event task.
     *
     * @param name Name of Event task.
     * @param from Event start in LocalDateTime object.
     * @param to   Event end in LocalDateTime object.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.fromDate = from;
        this.toDate = to;
    }

    /**
     * Returns Event object for the Event task.
     * Overloaded constructor for session restore from data file.
     *
     * @param name   Name of Event task.
     * @param isDone Status of Event task.
     * @param from   Event start in LocalDateTime object.
     * @param to     Event end in LocalDateTime object.
     */
    public Event(String name, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(name, isDone);
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(Event.FORMAT)
                + " to: " + this.toDate.format(Event.FORMAT) + ")";
    }

    /**
     * Returns formatted string for saving to data file.
     */
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.fromDate.format(Event.FORMAT)
                + " || " + this.toDate.format(Event.FORMAT);
    }
}
