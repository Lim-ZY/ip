package mark.command;

import mark.Storage;
import mark.task.TaskList;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    protected String response = "";
    public abstract void execute(TaskList tasks, Storage storage);

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return response;
    }
}
