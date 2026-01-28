import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }
    
    public Deadline(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
    
    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() +  " | " + this.deadline.toString();
    }
}
