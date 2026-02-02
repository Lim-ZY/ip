package mark;

/**
 * Represents a command which lists all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
