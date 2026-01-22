import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int length;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void printTasks() {
        int i = 1;
        for (Task t : this.tasks) {
            System.out.println(i++ + ". " + t.toString());
        }
    }
}