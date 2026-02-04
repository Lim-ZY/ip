package mark.command;

import mark.Storage;
import mark.task.TaskList;
import mark.Ui;

/**
 * Represents a command that is unrecognised.
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.println("I'm sorry, I don't know what that means :( Please try again.");
        Ui.printDivider();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
