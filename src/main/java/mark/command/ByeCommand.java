package mark.command;

import mark.Storage;
import mark.task.TaskList;
import mark.Ui;

/**
 * Represents a command that signals to end the session.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.saveTasks(storage);
        Ui.bye();
        this.response = Ui.getByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
