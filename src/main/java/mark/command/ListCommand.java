package mark.command;

import mark.Storage;
import mark.Ui;
import mark.task.TaskList;

/**
 * Represents a command which lists all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.printTasks();
        this.response = Ui.getAllTasksMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
