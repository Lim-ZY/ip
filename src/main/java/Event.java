import java.time.LocalDate;

public class Event extends Task {
    LocalDate fromDate;
    LocalDate toDate;

    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.fromDate = from;
        this.toDate = to;
    }
    
    public Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        super(name, isDone);
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate + " to: " + this.toDate + ")";
    }
    
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.fromDate.toString() + " || " + this.toDate.toString();
    }
}
