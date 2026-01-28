public class Event extends Task {
    String fromDate;
    String toDate;

    public Event(String name, String from, String to) {
        super(name);
        this.fromDate = from;
        this.toDate = to;
    }
    
    public Event(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate + " to: " + this.toDate + ")";
    }
}
