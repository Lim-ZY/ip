public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String doneState = this.isDone ? "X" : " ";
        return String.valueOf("[" + doneState + "] " + this.name);
    }
}