package mark.command;

import mark.MarkException;
import mark.Storage;
import mark.task.TaskList;
import mark.Ui;

/**
 * Represents a command that deletes the task specified when executed.
 */
public class DeleteCommand extends Command {
    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            this.response = Ui.getDeletedTaskMessage(tasks.getTask(this.id), tasks.length() - 1);
            tasks.delete(this.id);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
