package mark.command;

import mark.Storage;
import mark.task.TaskList;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    protected String response = "";
    public abstract void execute(TaskList tasks, Storage storage);
    public abstract boolean isExit();

    @Override
    public String toString() {
        return response;
    }
}
