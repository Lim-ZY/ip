package mark;

import java.util.List;

/**
 * Represents a command which finds tasks with matching names from the task list.
 */
public class FindCommand extends Command {
    private final String[] input;

    public FindCommand(String[] input) {
        this.input = input;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = input[1];
        try {
            List<Task> result = tasks.find(keyword);
            Ui.printTasksFound(result);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
