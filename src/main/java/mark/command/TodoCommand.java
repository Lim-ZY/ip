package mark.command;

import mark.InvalidFormatException;
import mark.Storage;
import mark.task.TaskList;
import mark.task.Todo;
import mark.Ui;

/**
 * Represents a command which creates a Todo task in the task list.
 */
public class TodoCommand extends Command {
    private final String[] input;

    public TodoCommand(String[] input) throws InvalidFormatException {
        if (input.length != 2 || input[1].isBlank()) {
            throw new InvalidFormatException("Usage: todo <task>");
        }
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        String todo = input[1];
        if (todo.isEmpty()) {
            Ui.println("Oh no... the description of a todo cannot be empty. Please try again.");
            Ui.printDivider();
        } else {
            Todo task = new Todo(input[1]);
            tasks.addTask(task);
            this.response = Ui.getAddedTaskMessage(task, tasks.length());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
