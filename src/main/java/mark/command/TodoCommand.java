package mark.command;

import mark.Storage;
import mark.task.TaskList;
import mark.task.Todo;
import mark.Ui;

/**
 * Represents a command which creates a Todo task in the task list.
 */
public class TodoCommand extends Command {
    private final String[] input;

    public TodoCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String todo = input[1];
        if (todo.isEmpty()) {
            Ui.println("Oh no... the description of a todo cannot be empty. Please try again.");
            Ui.printDivider();
        } else {
            tasks.addTask(new Todo(input[1]));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
