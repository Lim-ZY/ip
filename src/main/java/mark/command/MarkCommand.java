package mark.command;

import mark.MarkException;
import mark.Storage;
import mark.TaskList;
import mark.Ui;

/**
 * Represents a command which marks a task in the task list as done.
 */
public class MarkCommand extends Command {
    private final int id;

    /**
     * Returns a MarkCommand with the specified id.
     *
     * @param id int.
     */
    public MarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(this.id);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
