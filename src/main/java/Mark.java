import java.io.IOException;

public class Mark {
    private boolean running;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Mark() {
        this.running = true;
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Mark mark = new Mark();
        mark.run();
    }

    private void run() {
        this.storage = new Storage("./data/data.txt");
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
