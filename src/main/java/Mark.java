import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mark {
    private boolean running;
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
        greet();
        String line = "_____________________________________\n";
        while (this.running) {
            String input = br.readLine().trim();
            pw.println(line);
            switch (input) {
                case "bye":
                    this.bye();
                    this.running = false;
                    break;
                default:
                    pw.println(input);
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
