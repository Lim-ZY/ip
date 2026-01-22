import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int length() {
        return this.tasks.size();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public boolean markDone(int id) {
        if (id >= tasks.size()) {
            System.out.println("ID not defined. Please try again.");
            return false;
        }
        this.tasks.get(id).markDone();
        return true;
    }

    public boolean markUndone(int id) {
        if (id >= tasks.size()) {
            System.out.println("ID not defined. Please try again.");
            return false;
        }
        this.tasks.get(id).markUndone();
        return true;
    }

    public void printTask(int id) {
        if (id >= tasks.size()) {
            System.out.println("ID not defined. Please try again.");
        }
        System.out.println(this.tasks.get(id).toString());
    }

    public void printTasks() {
        int i = 1;
        for (Task t : this.tasks) {
            System.out.println(i++ + ". " + t.toString());
        }
    }
}