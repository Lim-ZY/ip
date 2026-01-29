package mark;

public class MarkCommand extends Command {
    private final int id;
    
    public MarkCommand(int id) {
        this.id = id;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(this.id);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
