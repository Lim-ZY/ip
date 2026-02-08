package mark.command;

import mark.Storage;
import mark.Ui;
import mark.task.TaskList;

/**
 * Represents a command that is unrecognised.
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        String message = "I'm sorry, I don't know what that means :( Please try again.";
        Ui.println(message);
        Ui.printDivider();
        this.response = message;
    }
}
