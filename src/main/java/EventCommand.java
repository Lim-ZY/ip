import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String taskName;
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;
    
    public EventCommand(String taskName, LocalDateTime fromDate, LocalDateTime toDate) {
        this.taskName = taskName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(this.taskName, this.fromDate, this.toDate));
    }

    @Override
    boolean isExit() {
        return false;
    }
}
