package mark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    /** Input format of date and time **/
    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Returns Event object for the Event task.
     * 
     * @param name Name of Event task.
     * @param from Event start in LocalDateTime object.
     * @param to Event end in LocalDateTime object.
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
     * @param name Name of Event task.
     * @param isDone Status of Event task.
     * @param from Event start in LocalDateTime object.
     * @param to Event end in LocalDateTime object.
     */
    public Event(String name, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(name, isDone);
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(Event.dtf) 
                + " to: " + this.toDate.format(Event.dtf) + ")";
    }

    /**
     * Returns formatted string for saving to data file.
     */
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.fromDate.format(Event.dtf) 
                + " || " + this.toDate.format(Event.dtf);
    }
}
