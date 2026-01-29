import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String taskName;
    private final LocalDateTime date;
    
    public DeadlineCommand(String taskName, LocalDateTime date) {
        this.taskName = taskName;
        this.date = date;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(this.taskName, this.date));
    }

    @Override
    boolean isExit() {
        return false;
    }
}
