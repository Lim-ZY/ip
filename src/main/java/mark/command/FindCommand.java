package mark.command;

import java.util.List;

import mark.MarkException;
import mark.Storage;
import mark.task.Task;
import mark.task.TaskList;
import mark.Ui;

/**
 * Represents a command which finds tasks with matching names from the task list.
 */
public class FindCommand extends Command {
    private final String[] input;

    public FindCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = input[1];
        try {
            List<Task> result = tasks.find(keyword);
            Ui.printTasksFound(result);
            this.response = Ui.getTasksFoundMessage(result);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
