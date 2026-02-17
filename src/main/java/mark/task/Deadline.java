package mark.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import mark.MarkException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    /**
     * Input format of date and time.
     **/
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private LocalDateTime deadline;

    /**
     * Returns Deadline object for the Deadline task.
     *
     * @param name     Name of deadline task.
     * @param deadline Deadline of task in LocalDateTime object.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns Deadline object for the Deadline task.
     * Overloaded constructor for session restore from data file.
     *
     * @param name     Name of deadline task.
     * @param isDone   Status of deadline task.
     * @param deadline Deadline of task in LocalDateTime object.
     */
    public Deadline(String name, boolean isDone, LocalDateTime deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public void update(Map<String, String> fields, String errorMessage) throws MarkException {
        for (String fieldName : fields.keySet()) {
            switch (fieldName) {
            case "taskName":
                super.update(fields, errorMessage);
                break;
            case "by":
                String by = fields.get("by");
                this.deadline = LocalDateTime.parse(by, OUTPUT_DATETIME_FORMAT);
                break;
            default:
                throw new MarkException(errorMessage);
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(Deadline.OUTPUT_DATETIME_FORMAT) + ")";
    }

    /**
     * Returns formatted string for saving to data file.
     */
    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + this.deadline.format(Deadline.OUTPUT_DATETIME_FORMAT);
    }
}
