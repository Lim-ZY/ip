import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int length() {
        return this.tasks.size();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        Ui.printAddedTask(t, this.length());
    }

    public void markDone(int id) throws MarkException {
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        this.tasks.get(id).markDone();
        Ui.markDone(this.tasks.get(id));
    }

    public void markUndone(int id) throws MarkException {
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        this.tasks.get(id).markUndone();
        Ui.markUndone(this.tasks.get(id));
    }

    public void delete(int id) throws MarkException {
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        Ui.printDeletedTask(this.tasks.get(id),  this.length() - 1);
        this.tasks.remove(id);
    }

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
    
    public void saveTasks(Storage storage) {
        storage.save(this.tasks);
    }
}