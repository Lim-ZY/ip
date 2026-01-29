package mark;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Returns empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns loaded TaskList object.
     * 
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns length of TaskList.
     */
    public int length() {
        return this.tasks.size();
    }

    /**
     * Adds task to TaskList.
     * 
     * @param t Task to add.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        Ui.printAddedTask(t, this.length());
    }

    /**
     * Marks task as done.
     * 
     * @param id ID of task.
     * @throws MarkException If ID is invalid.
     */
    public void markDone(int id) throws MarkException {
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        this.tasks.get(id).markDone();
        Ui.markDone(this.tasks.get(id));
    }

    /**
     * Marks task as undone.
     * 
     * @param id ID of task.
     * @throws MarkException If ID is invalid.
     */
    public void markUndone(int id) throws MarkException {
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        this.tasks.get(id).markUndone();
        Ui.markUndone(this.tasks.get(id));
    }

    /**
     * Deletes task in TaskList.
     * 
     * @param id ID of task to delete.
     * @throws MarkException If ID is invalid.
     */
    public void delete(int id) throws MarkException {
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        Ui.printDeletedTask(this.tasks.get(id),  this.length() - 1);
        this.tasks.remove(id);
    }

    /**
     * Prints all tasks in TaskList.
     */
    public void printTasks() {
        if (this.tasks.isEmpty()) {
            Ui.println("No tasks in the list.");
            return;
        }
        int i = 1;
        for (Task t : this.tasks) {
            Ui.println(i++ + ". " + t.toString());
        }
        Ui.printDivider();
    }

    /**
     * Passes TaskList to storage for saving.
     * 
     * @param storage Storage instance.
     */
    public void saveTasks(Storage storage) {
        storage.save(this.tasks);
    }
}