package mark.command;

import mark.Storage;
import mark.task.TaskList;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage);
    public abstract boolean isExit();
    public String response = "";

    @Override
    public String toString() {
        return response;
    }
}
