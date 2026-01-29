package mark;

public class UnmarkCommand extends Command {
    private final int id;
    
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
