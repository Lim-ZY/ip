import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }
    
    public Deadline(String name, boolean isDone, LocalDateTime deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(Deadline.dtf) + ")";
    }
    
    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() +  " | " + this.deadline.format(Deadline.dtf);
    }
}
