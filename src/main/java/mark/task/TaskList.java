package mark.task;

import java.util.ArrayList;
import java.util.List;

import mark.MarkException;
import mark.Storage;
import mark.Ui;

/**
 * Represents a collection of tasks.
 * Provides methods to add, delete, mark, unmark, and find tasks.
 */
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
     * Returns a task at the given task number.
     */
    public Task getTask(int id) {
        return this.tasks.get(id);
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
        Ui.printDeletedTask(this.tasks.get(id), this.length() - 1);
        this.tasks.remove(id);
    }

    /**
     * Returns a list of tasks with names that matches the keyword.
     *
     * @param keyword String.
     * @return List of tasks.
     * @throws MarkException if keyword is null or empty.
     */
    public List<Task> find(String keyword) throws MarkException {
        List<Task> result = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) {
            throw new MarkException("Keyword is null or empty.");
        }
        for (Task t : this.tasks) {
            if (t.nameContains(keyword)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Prints all tasks in TaskList.
     */
    public void printTasks() {
        if (this.tasks.isEmpty()) {
            Ui.println("No tasks in the list.");
            return;
        }
        Ui.print(this.toString());
        Ui.printDivider();
    }

    /**
     * Returns tasks in string representation.
     */
    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "No tasks in the list.";
        }

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task t : this.tasks) {
            sb.append(i++ + ". " + t.toString() + "\n");
        }
        return sb.toString();
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
