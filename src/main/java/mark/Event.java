package mark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime fromDate;
    LocalDateTime toDate;
    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.fromDate = from;
        this.toDate = to;
    }
    
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
    
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.fromDate.format(Event.dtf) 
                + " || " + this.toDate.format(Event.dtf);
    }
}
