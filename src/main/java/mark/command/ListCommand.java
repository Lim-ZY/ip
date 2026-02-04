package mark.command;

import mark.Storage;
import mark.task.TaskList;
import mark.Ui;

/**
 * Represents a command which lists all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
