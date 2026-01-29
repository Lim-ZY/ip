package mark;

public class UnknownCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.println("I'm sorry, I don't know what that means :( Please try again.");
        Ui.printDivider();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
