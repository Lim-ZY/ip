import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Mark {
    private boolean running;
    private TaskList tasks;
    private BufferedReader br;
    private PrintWriter pw;

    public Mark(boolean running, BufferedReader br, PrintWriter pw) {
        this.running = running;
        this.br = br;
        this.pw = pw;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        Mark mark = new Mark(true, br, pw);
        try {
            mark.run();
        } catch (IOException e) {
            pw.println(e.getMessage());
        }
        pw.flush();
    }

    private void run() throws IOException {
        Storage storage = new Storage("./data/data.txt");
        this.tasks = new TaskList(storage.getFileContents());
        
        greet();
        String line = "_____________________________________";
        while (this.running) {
            try {
                String[] input = br.readLine().trim().split(" ", 2);
                pw.println(line);
                pw.flush();

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
                        pw.println(e.getMessage());
                        pw.println(line);
                        pw.flush();
                    }
                    continue;
                }
                case "unmark": {
                    try {
                        int id = Integer.parseInt(input[1]) - 1;
                        this.tasks.markUndone(id);
                    } catch (MarkException e) {
                        pw.println(e.getMessage());
                        pw.println(line);
                        pw.flush();
                    }
                    continue;
                }
                case "delete": {
                try {
                        int id = Integer.parseInt(input[1]) - 1;
                        this.tasks.delete(id);
                    } catch (MarkException e) {
                        pw.println(e.getMessage());
                        pw.println(line);
                        pw.flush();
                    }
                    continue;
                }
                case "todo": {
                    if (input[1].isEmpty()) {
                        pw.println("Oh no... the description of a todo cannot be empty. Please try again.");
                        pw.print(line);
                        pw.flush();
                    } else {
                        this.tasks.addTask(new Todo(input[1]));
                    }
                    break;
                }
                case "deadline": {
                    if (!input[1].contains("/by")) {
                        throw new MarkException("usage: deadline <task> /by <YYYY-MM-DD>");
                    }
                    String deadline = input[1].substring(input[1].indexOf("/by") + 4);
                    String taskName = input[1].substring(0, input[1].indexOf("/by")).trim();
                    LocalDate date;
                    try {
                        date = LocalDate.parse(deadline);
                    } catch (DateTimeParseException e) {
                        throw new MarkException("usage: deadline <task> /by <YYYY-MM-DD>");
                    }
                    this.tasks.addTask(new Deadline(taskName, date));
                    break;
                }
                case "event": {
                    if (!input[1].contains("/from") || !input[1].contains("/to")) {
                        throw new MarkException("usage: event <task> /from <YYYY-MM-DD> /to <YYYY-MM-DD>");
                    }
                    LocalDate fromDate, toDate;
                    try {
                        fromDate = LocalDate.parse(input[1].substring(input[1].indexOf("/from") + 6,
                                input[1].indexOf("/to")).trim());
                        toDate = LocalDate.parse(input[1].substring(input[1].indexOf("/to") + 4));
                    }  catch (DateTimeParseException e) {
                        throw new MarkException("usage: event <task> /from <YYYY-MM-DD> /to <YYYY-MM-DD>");
                    }
                    String taskName = input[1].substring(0, input[1].indexOf("/from")).trim();
                    this.tasks.addTask(new Event(taskName, fromDate, toDate));
                    break;
                }
                default: {
                    throw new MarkException("I'm sorry, I don't know what that means :( Please try again.");
                }}
                
                pw.print("Got it. I've added this task:\n\t");
                pw.flush();
                this.tasks.printTask(this.tasks.length() - 1);
                pw.println("Now you have " + this.tasks.length() + " tasks in the list.");

                pw.println(line);
                pw.flush();
            } catch (MarkException e) {
                pw.println(e.getMessage());
                pw.println(line);
                pw.flush();
            }
        }
    }

    private void greet() {
        String line = "_____________________________________\n";
        String logo = "  __  __            _    \n" 
                + " |  \\/  |          | |   \n" 
                + " | \\  / | __ _ _ __| | __\n" 
                + " | |\\/| |/ _` | '__| |/ /\n" 
                + " | |  | | (_| | |  |   < \n" 
                + " |_|  |_|\\__,_|_|  |_|\\_\\\n";
        pw.println(line + "Hello! I'm \n" + logo + "What can I do for you?\n" + line);
        pw.flush();
    }

    private void bye(Storage storage) {
        this.tasks.saveTasks(storage);
        pw.println("Bye. Hope to see you again soon!");
        pw.flush();
        System.out.print("_____________________________________\n");
    }
}
