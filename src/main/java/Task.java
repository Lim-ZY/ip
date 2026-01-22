public class Task {
    private final String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String doneState = this.done ? "X" : " ";
        return String.valueOf("[" + doneState + "] " + this.name);
    }
}