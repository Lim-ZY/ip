package mark.command;

import mark.Storage;
import mark.TaskList;
import mark.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
