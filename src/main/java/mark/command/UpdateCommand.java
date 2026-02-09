package mark.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import mark.MarkException;
import mark.Storage;
import mark.Ui;
import mark.task.Deadline;
import mark.task.Event;
import mark.task.Task;
import mark.task.TaskList;
import mark.task.Todo;

/**
 * Represents a command which updates a specified task in the task list.
 */
public class UpdateCommand extends Command {
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String INVALID_UPDATE_FORMAT_ERROR = "Please specify each valid update pair: "
            + "<fieldName> <fieldValue>";

    private int id;
    private Map<String, String> fieldValuePairs;

    public UpdateCommand(int id) {
        this.id = id;
    }

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
        if (toUpdate instanceof Todo) {
            Todo t = (Todo) toUpdate;
            if (!fieldValuePairs.containsKey("taskName")) {
                this.response = INVALID_UPDATE_FORMAT_ERROR;
                return;
            }
            String name = this.fieldValuePairs.get("taskName");
            t.updateName(name);
            this.response = Ui.getUpdatedTaskMessage(this.id + 1, t);
        } else if (toUpdate instanceof Deadline) {
            Deadline deadlineToUpdate = (Deadline) toUpdate;
            try {
                for (String fieldName : this.fieldValuePairs.keySet()) {
                    switch (fieldName) {
                    case "taskName":
                        String name = this.fieldValuePairs.get("taskName");
                        deadlineToUpdate.updateName(name);
                        break;
                    case "by":
                        String by = this.fieldValuePairs.get("by");
                        deadlineToUpdate.updateDeadline(LocalDateTime.parse(by, OUTPUT_DATETIME_FORMAT));
                        break;
                    default:
                        throw new MarkException(INVALID_UPDATE_FORMAT_ERROR);
                    }
                }
                this.response = Ui.getUpdatedTaskMessage(this.id + 1, deadlineToUpdate);
            } catch (MarkException e) {
                Ui.printException(e);
            }
        } else if (toUpdate instanceof Event) {
            Event eventToUpdate = (Event) toUpdate;
            try {
                for (String fieldName : this.fieldValuePairs.keySet()) {
                    switch (fieldName) {
                    case "taskName":
                        String name = this.fieldValuePairs.get("taskName");
                        eventToUpdate.updateName(name);
                        break;
                    case "from":
                        String from = this.fieldValuePairs.get("from");
                        eventToUpdate.updateFromDate(LocalDateTime.parse(from, OUTPUT_DATETIME_FORMAT));
                        break;
                    case "to":
                        String to = this.fieldValuePairs.get("to");
                        eventToUpdate.updateToDate(LocalDateTime.parse(to, OUTPUT_DATETIME_FORMAT));
                        break;
                    default:
                        throw new MarkException(INVALID_UPDATE_FORMAT_ERROR);
                    }
                }
                this.response = Ui.getUpdatedTaskMessage(this.id + 1, eventToUpdate);
            } catch (MarkException e) {
                Ui.printException(e);
            }
        }
    }
}
