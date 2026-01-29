import java.util.List;

public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks,  Ui ui, Storage storage) {
        tasks.printTasks();
    }
    
    @Override
    boolean isExit() {
        return false;
    }
}
