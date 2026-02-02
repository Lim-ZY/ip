package mark;

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
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markUndone(this.id);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
