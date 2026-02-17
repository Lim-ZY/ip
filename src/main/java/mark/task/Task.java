package mark.task;

import java.util.Map;

import mark.MarkException;

/**
 * Represents a general task with a name and done status.
 * Serves as a superclass for more specific tasks.
 */
public class Task {
    private String name;
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
     * @param name   Name of task.
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

    public boolean nameContains(String keyword) {
        return this.name.contains(keyword);
    }

    public void updateName(String name) {
        this.name = name;
    }

    /**
     * Updates task name and throws MarkException with the specified errorMessage.
     *
     * @param fields Map of fields in task to their requested update value.
     * @param errorMessage Error message to pass to MarkException.
     */
    public void update(Map<String, String> fields, String errorMessage) throws MarkException {
        if (!fields.containsKey("taskName")) {
            throw new MarkException(errorMessage);
        }
        this.name = fields.get("taskName");
    }

    @Override
    public String toString() {
        String doneState = this.isDone ? "X" : " ";
        return "[" + doneState + "] " + this.name;
    }

    /**
     * Returns formatted string for saving to data file.
     */
    public String toSaveString() {
        return (this.isDone ? "1" : "0") + " | " + this.name;
    }
}
