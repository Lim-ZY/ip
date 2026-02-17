package mark.command;

import java.util.Map;

import mark.MarkException;
import mark.Storage;
import mark.Ui;
import mark.task.Task;
import mark.task.TaskList;

/**
 * Represents a command which updates a specified task in the task list.
 */
public class UpdateCommand extends Command {
    private static final String INVALID_UPDATE_FORMAT_ERROR = "Please specify each valid update pair: "
            + "<fieldName> <fieldValue>";

    private int id;
    private Map<String, String> fieldValuePairs;

    /**
     * Returns an UpdateCommand with the task id to update, and a hashmap with fields to update.
     *
     * @param id int.
     * @param fieldValuePairs Map of fields to values.
     */
    public UpdateCommand(int id, Map<String, String> fieldValuePairs) {
        this.id = id;
        this.fieldValuePairs = fieldValuePairs;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task toUpdate = tasks.getTask(this.id);
        try {
            toUpdate.update(this.fieldValuePairs, INVALID_UPDATE_FORMAT_ERROR);
            this.response = Ui.getUpdatedTaskMessage(this.id + 1, toUpdate);
        } catch (MarkException e) {
            Ui.printException(e);
            this.response = e.getMessage();
        }
    }
}
