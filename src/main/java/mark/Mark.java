package mark;

import java.io.IOException;

import mark.command.Command;
import mark.task.TaskList;

/**
 * The main class of the Mark application.
 */
public class Mark {
    private boolean isRunning;
    private TaskList tasks;
    private Storage storage;

    /**
     * Returns a Mark object with initialised Storage object.
     */
    public Mark() {
        this.isRunning = true;
        this.storage = new Storage("./data/data.txt");

        try {
            this.tasks = new TaskList(this.storage.getFileContents());
        } catch (IOException e) {
            Ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Mark mark = new Mark();
        mark.run();
    }

    private void run() {
        Ui.greet();

        while (this.isRunning) {
            try {
                Command c = Parser.parse(Ui.readInput());
                c.execute(this.tasks, this.storage);
                this.isRunning = !c.isExit();
            } catch (InvalidFormatException | IOException e) {
                Ui.printException(e);
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this.tasks, this.storage);
            return c.toString();
        } catch (InvalidFormatException e) {
            return "Error: " + e.getMessage();
        }
    }

    public boolean isExit(String input) {
        try {
            Command c = Parser.parse(input);
            return c.isExit();
        } catch (InvalidFormatException e) {
            return false;
        }
    }

    public String getGreetingMessage() {
        return Ui.getGreetingMessage();
    }
}
