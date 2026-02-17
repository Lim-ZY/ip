package mark.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import mark.MarkException;

/**
 * Represents a task that occurs over a specific duration.
 */
public class Event extends Task {
    /**
     * Input format of date and time.
     **/
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

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
    public void update(Map<String, String> fields, String errorMessage) throws MarkException {
        for (String fieldName : fields.keySet()) {
            switch (fieldName) {
            case "taskName":
                super.update(fields, errorMessage);
                break;
            case "from":
                String from = fields.get("from");
                this.fromDate = LocalDateTime.parse(from, OUTPUT_DATETIME_FORMAT);
                break;
            case "to":
                String to = fields.get("to");
                this.toDate = LocalDateTime.parse(to, OUTPUT_DATETIME_FORMAT);
                break;
            default:
                throw new MarkException(errorMessage);
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(Event.OUTPUT_DATETIME_FORMAT)
                + " to: " + this.toDate.format(Event.OUTPUT_DATETIME_FORMAT) + ")";
    }

    /**
     * Returns formatted string for saving to data file.
     */
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.fromDate.format(Event.OUTPUT_DATETIME_FORMAT)
                + " || " + this.toDate.format(Event.OUTPUT_DATETIME_FORMAT);
    }
}
