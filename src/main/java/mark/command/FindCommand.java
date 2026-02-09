package mark.command;

import java.util.List;

import mark.MarkException;
import mark.Storage;
import mark.Ui;
import mark.task.Task;
import mark.task.TaskList;

/**
 * Represents a command which finds tasks with matching names from the task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            List<Task> result = tasks.find(keyword);
            Ui.printTasksFound(result);
            this.response = Ui.getTasksFoundMessage(result);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }
}
