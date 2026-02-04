package mark.command;

import mark.MarkException;
import mark.Storage;
import mark.task.TaskList;
import mark.Ui;

/**
 * Represents a command which marks a task in the task list as undone.
 */
public class UnmarkCommand extends Command {
    private final int id;

    /**
     * Returns an UnmarkCommand with the specified id.
     *
     * @param id int.
     */
    public UnmarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markUndone(this.id);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
