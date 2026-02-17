package mark.command;

import mark.InvalidFormatException;
import mark.Storage;
import mark.Ui;
import mark.task.TaskList;
import mark.task.Todo;

/**
 * Represents a command which creates a Todo task in the task list.
 */
public class TodoCommand extends Command {
    private final String taskName;

    /**
     * Returns a TodoCommand object.
     *
     * @param task User provided task.
     */
    public TodoCommand(String task) {
        this.taskName = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Todo task = new Todo(taskName);
        tasks.addTask(task);
        this.response = Ui.getAddedTaskMessage(task, tasks.length());
    }
}
