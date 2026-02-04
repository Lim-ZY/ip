package mark.command;

import java.time.LocalDateTime;

import mark.task.Deadline;
import mark.Storage;
import mark.TaskList;
import mark.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(this.taskName, this.date));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
