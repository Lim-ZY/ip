import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mark {
    private boolean running;
    private TaskList tasks;
    private BufferedReader br;
    private PrintWriter pw;

    public Mark(boolean running, BufferedReader br, PrintWriter pw) {
        this.running = running;
        this.tasks = new TaskList();
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
        greet();
        String line = "_____________________________________";
        while (this.running) {
            String input = br.readLine().trim();
            pw.println(line);
            pw.flush();

            if (input.equals("list")) {
                this.tasks.printTasks();
            } else if (input.equals("bye")) {
                this.bye();
                this.running = false;
            } else if (input.startsWith("mark") && input.length() > 5) {
                int id = Integer.parseInt(input.substring(5)) - 1;
                if (this.tasks.markDone(id)) {
                    pw.print("Nice! I've marked this task as done:\n\t");
                    pw.flush();
                    this.tasks.printTask(id);
                }
            } else if (input.startsWith("unmark") && input.length() > 7) {
                int id = Integer.parseInt(input.substring(7)) - 1;
                if (this.tasks.markUndone(id)) {
                    pw.print("OK, I've marked this task as not done yet:\n\t");
                    pw.flush();
                    this.tasks.printTask(id);
                }
            } else {
                if (input.startsWith("todo") && input.length() > 5) {
                    this.tasks.addTask(new Todo(input.substring(5)));
                } else if (input.startsWith("deadline") && input.length() > 13 && input.contains("/by")) {
                    String taskName = input.substring(9, input.indexOf("/by")).trim();
                    String deadline = input.substring(input.indexOf("/by") + 4);
                    this.tasks.addTask(new Deadline(taskName, deadline));
                } else if (input.startsWith("event") && input.length() > 16 &&
                           input.contains("/from") && input.contains("/to")) {
                    String taskName = input.substring(6, input.indexOf("/from")).trim();
                    String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
                    String to = input.substring(input.indexOf("/to") + 4);
                    this.tasks.addTask(new Event(taskName, from, to));
                } else {
                    pw.println("Invalid input. Please try again.");
                    pw.println(line);
                    pw.flush();
                    continue;
                }
                pw.print("Got it. I've added this task:\n\t");
                pw.flush();
                this.tasks.printTask(this.tasks.length() - 1);
                pw.println("Now you have " + this.tasks.length() + " tasks in the list.");
            }
            pw.println(line);
            pw.flush();
        }
    }

    private void greet() {
        String line = "_____________________________________\n";
        String logo = "  __  __            _    \n" +
                " |  \\/  |          | |   \n" +
                " | \\  / | __ _ _ __| | __\n" +
                " | |\\/| |/ _` | '__| |/ /\n" +
                " | |  | | (_| | |  |   < \n" +
                " |_|  |_|\\__,_|_|  |_|\\_\\\n";
        pw.println(line + "Hello! I'm \n" + logo + "What can I do for you?\n" + line);
        pw.flush();
    }

    private void bye() {
        pw.println("Bye. Hope to see you again soon!");
        pw.flush();
    }
}
