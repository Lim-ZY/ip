package mark;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    abstract void execute(TaskList tasks, Ui ui, Storage storage);
    abstract boolean isExit();
}
