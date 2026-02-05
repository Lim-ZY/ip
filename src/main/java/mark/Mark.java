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

    /**
     * Starts the Mark instance for the CLI.
     *
     * @param args No args are needed.
     */
    public static void main(String[] args) {
        Mark mark = new Mark();
        mark.run();
    }

    /**
     * Starts the main loop and execution of Mark.
     */
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

    /**
     * Returns the response of Mark as a string in response to user input.
     *
     * @param input String.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this.tasks, this.storage);
            return c.toString();
        } catch (InvalidFormatException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Returns exit status of command in response to user input.
     *
     * @param input String.
     * @return true if user command is "bye".
     */
    public boolean isExit(String input) {
        try {
            Command c = Parser.parse(input);
            return c.isExit();
        } catch (InvalidFormatException e) {
            return false;
        }
    }

    /**
     * Returns the greeting message as a string.
     * Used for the GUI.
     *
     * @return String representation of greeting message.
     */
    public String getGreetingMessage() {
        return Ui.getGreetingMessage();
    }
}
