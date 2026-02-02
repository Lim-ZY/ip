package mark;

import java.time.LocalDateTime;

/**
 * Represents a command which creates a Deadline task in the task list.
 */
public class DeadlineCommand extends Command {
    private final String taskName;
    private final LocalDateTime date;

    /**
     * Returns a DeadlineCommand with the specified taskName and date.
     *
     * @param taskName String.
     * @param date     LocalDateTime.
     */
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
