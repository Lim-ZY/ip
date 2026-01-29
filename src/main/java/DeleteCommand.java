public class DeleteCommand extends Command {
    private final int id;
    
    public DeleteCommand(int id) {
        this.id = id;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.delete(this.id);
        } catch (MarkException e) {
            Ui.printException(e);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
