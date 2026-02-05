package mark.command;

import java.time.LocalDateTime;

import mark.Storage;
import mark.Ui;
import mark.task.Event;
import mark.task.TaskList;

/**
 * Represents a command which creates an Event task in the task list.
 */
public class EventCommand extends Command {
    private final String taskName;
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    /**
     * Returns an EventCommand with the specified taskName and date.
     *
     * @param taskName String.
     * @param fromDate LocalDateTime.
     * @param toDate   LocalDateTime.
     */
    public EventCommand(String taskName, LocalDateTime fromDate, LocalDateTime toDate) {
        this.taskName = taskName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Event task = new Event(this.taskName, this.fromDate, this.toDate);
        tasks.addTask(task);
        this.response = Ui.getAddedTaskMessage(task, tasks.length());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
