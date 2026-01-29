package mark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    /** Input format of date and time **/
    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Returns Deadline object for the Deadline task.
     * 
     * @param name Name of deadline task.
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
     * @param name Name of deadline task.
     * @param isDone Status of deadline task.
     * @param deadline Deadline of task in LocalDateTime object.
     */
    public Deadline(String name, boolean isDone, LocalDateTime deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(Deadline.dtf) + ")";
    }

    /**
     * Returns formatted string for saving to data file.
     */
    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() +  " | " + this.deadline.format(Deadline.dtf);
    }
}
