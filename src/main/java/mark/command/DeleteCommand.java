package mark.command;

import mark.MarkException;
import mark.Storage;
import mark.Ui;
import mark.task.Task;
import mark.task.TaskList;

/**
 * Represents a command that deletes the task specified when executed.
 */
public class DeleteCommand extends Command {
    private final int id;

    /**
     * Returns a DeleteCommand with the specified id.
     *
     * @param id ID of task in tasklist shown in UI to delete.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            if (id < 0 || id > tasks.length() - 1) {
                throw new MarkException("ID not defined. Please try again.");
            }
            Task currentTask = tasks.getTask(this.id);
            tasks.delete(this.id);
            this.response = Ui.getDeletedTaskMessage(currentTask, tasks.length());
        } catch (MarkException e) {
            Ui.printException(e);
            this.response = e.getMessage();
        }
    }
}
