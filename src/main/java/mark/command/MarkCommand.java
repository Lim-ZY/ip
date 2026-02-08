package mark.command;

import mark.MarkException;
import mark.Storage;
import mark.Ui;
import mark.task.TaskList;

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
    public void execute(TaskList tasks, Storage storage) {
        try {
            tasks.markDone(this.id);
            this.response = Ui.getMarkDoneMessage(tasks.getTask(this.id));
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }
}
