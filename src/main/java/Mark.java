import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Mark {
    private boolean running;
    private TaskList tasks;
    private Ui ui;

    public Mark() {
        this.running = true;
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Mark mark = new Mark();
        try {
            mark.run();
        } catch (IOException e) {
            Ui.println(e.getMessage());
        }
    }

    private void run() throws IOException {
        Storage storage = new Storage("./data/data.txt");
        try {
            this.tasks = new TaskList(storage.getFileContents());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
        
        ui.greet();
        
        while (this.running) {
            try {
                String[] input = ui.readInput();
                Ui.printDivider();

                switch (input[0]) {
                case "list": {
                    this.tasks.printTasks();
                    continue;
                }
                case "bye": {
                    this.bye(storage);
                    this.running = false;
                    continue;
                }
                case "mark": {
                    try {
                        int id = Integer.parseInt(input[1]) - 1;
                        this.tasks.markDone(id);
                    } catch (MarkException e) {
                        Ui.printException(e);
                    }
                    continue;
                }
                case "unmark": {
                    try {
                        int id = Integer.parseInt(input[1]) - 1;
                        this.tasks.markUndone(id);
                    } catch (MarkException e) {
                        Ui.printException(e);
                    }
                    continue;
                }
                case "delete": {
                    try {
                        int id = Integer.parseInt(input[1]) - 1;
                        this.tasks.delete(id);
                    } catch (MarkException e) {
                        Ui.printException(e);
                    }
                    continue;
                }
                case "todo": {
                    if (input[1].isEmpty()) {
                        Ui.println("Oh no... the description of a todo cannot be empty. Please try again.");
                        Ui.printDivider();
                    } else {
                        this.tasks.addTask(new Todo(input[1]));
                    }
                    break;
                }
                case "deadline": {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    if (!input[1].contains("/by")) {
                        throw new MarkException("usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
                    }
                    String deadline = input[1].substring(input[1].indexOf("/by") + 4);
                    String taskName = input[1].substring(0, input[1].indexOf("/by")).trim();
                    LocalDateTime date;
                    try {
                        date = LocalDateTime.parse(deadline, dtf);
                    } catch (DateTimeParseException e) {
                        throw new MarkException("usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
                    }
                    this.tasks.addTask(new Deadline(taskName, date));
                    break;
                }
                case "event": {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    if (!input[1].contains("/from") || !input[1].contains("/to")) {
                        throw new MarkException("usage: event <task> /from <YYYY-MM-DD> <HHMM> "
                                + "/to <YYYY-MM-DD> <HHMM>");
                    }
                    LocalDateTime fromDate, toDate;
                    try {
                        fromDate = LocalDateTime.parse(input[1].substring(input[1].indexOf("/from") + 6,
                                input[1].indexOf("/to")).trim(), dtf);
                        toDate = LocalDateTime.parse(input[1].substring(input[1].indexOf("/to") + 4), dtf);
                    } catch (DateTimeParseException e) {
                        throw new MarkException("usage: event <task> /from <YYYY-MM-DD> <HHMM> " 
                                + "/to <YYYY-MM-DD> <HHMM>");
                    }
                    String taskName = input[1].substring(0, input[1].indexOf("/from")).trim();
                    this.tasks.addTask(new Event(taskName, fromDate, toDate));
                    break;
                }
                default: {
                    throw new MarkException("I'm sorry, I don't know what that means :( Please try again.");
                }}
                
                Ui.print("Got it. I've added this task:\n\t");
                this.tasks.printTask(this.tasks.length() - 1);
                Ui.println("Now you have " + this.tasks.length() + " tasks in the list.");
                Ui.printDivider();
            } catch (MarkException e) {
                Ui.printException(e);
            }
        }
    }

    private void bye(Storage storage) {
        this.tasks.saveTasks(storage);
        ui.bye();
    }
}
