package mark;

import java.io.IOException;

/**
 * The main class of the Mark application.
 */
public class Mark {
    private boolean running;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Returns a Mark object with initialised Ui and Storage objects.
     */
    public Mark() {
        this.running = true;
        this.ui = new Ui();
        this.storage = new Storage("./data/data.txt");
    }

    public static void main(String[] args) {
        Mark mark = new Mark();
        mark.run();
    }

    private void run() {
        try {
            this.tasks = new TaskList(this.storage.getFileContents());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }

        ui.greet();

        while (this.running) {
            try {
                Command c = Parser.parse(ui.readInput());
                c.execute(this.tasks, this.ui, this.storage);
                this.running = !c.isExit();
            } catch (InvalidFormatException | IOException e) {
                Ui.printException(e);
            }
        }
    }
}
