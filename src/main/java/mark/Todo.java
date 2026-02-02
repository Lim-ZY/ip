package mark;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Returns Todo object for the Todo task.
     *
     * @param name Name of task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns Todo object for the Todo task.
     * Overloaded constructor for session restore from data file.
     *
     * @param name   Name of task.
     * @param isDone Status of task.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns formatted string for saving to data file.
     */
    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }
}
