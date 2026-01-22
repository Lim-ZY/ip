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

    public void markDone(int id) throws MarkException{
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        this.tasks.get(id).markDone();
        System.out.print("Nice! I've marked this task as done:\n\t");
        this.printTask(id);
        System.out.print("_____________________________________\n");
    }

    public void markUndone(int id) throws MarkException {
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        this.tasks.get(id).markUndone();
        System.out.print("OK, I've marked this task as not done yet:\n\t");
        this.printTask(id);
        System.out.print("_____________________________________\n");
    }
    
    public void delete(int id) throws MarkException {
        if (id >= tasks.size() || id < 0) {
            throw new MarkException("ID not defined. Please try again.");
        }
        System.out.print("Noted. I've removed this task:\n\t");
        this.printTask(id);
        this.tasks.remove(id);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.print("_____________________________________\n");
    }

    public void printTask(int id) {
        System.out.println(this.tasks.get(id).toString());
    }

    public void printTasks() {
        int i = 1;
        for (Task t : this.tasks) {
            System.out.println(i++ + ". " + t.toString());
        }
        System.out.print("_____________________________________\n");
    }
}