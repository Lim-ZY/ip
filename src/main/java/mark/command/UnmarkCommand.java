package mark.command;

import mark.MarkException;
import mark.Storage;
import mark.Ui;
import mark.task.TaskList;

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
    public void execute(TaskList tasks, Storage storage) {
        try {
            tasks.markUndone(this.id);
            this.response = Ui.getMarkUndoneMessage(tasks.getTask(this.id));
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }
}
