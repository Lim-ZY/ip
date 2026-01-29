package mark;

public class ByeCommand extends Command {
    @Override
    void execute(TaskList tasks,  Ui ui, Storage storage) {
        tasks.saveTasks(storage);
        ui.bye();
    }

    @Override
    boolean isExit() {
        return true;
    }
}
