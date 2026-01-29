package mark;

public class Task {
    private final String name;
    private boolean isDone;

    /**
     * Returns Task object.
     * 
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns Task object.
     * Overloaded constructor for session restore from data file.
     * 
     * @param name Name of task.
     * @param isDone Status of task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String doneState = this.isDone ? "X" : " ";
        return String.valueOf("[" + doneState + "] " + this.name);
    }

    /**
     * Returns formatted string for saving to data file.
     */
    public String toSaveString() {
        return (this.isDone ? "1" : "0") + " | " + this.name;
    }
}