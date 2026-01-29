import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Ui {
    private static final PrintWriter pw =  new PrintWriter(System.out);
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void printDivider() {
        Ui.pw.println("_____________________________________");
        Ui.pw.flush();
    }
    
    public static void println(String s) {
        Ui.pw.println(s);
        Ui.pw.flush();
    }
    
    public static void print(String s) {
        Ui.pw.print(s);
        Ui.pw.flush();
    }
    
    public static void printException(Exception e) {
        Ui.pw.println(e.getMessage());
        Ui.printDivider();
        Ui.pw.flush();
    }
    
    public String[] readInput() throws IOException {
        return Ui.br.readLine().trim().split(" ", 2);
    }

    public void greet() {
        String logo = "  __  __            _    \n"
                + " |  \\/  |          | |   \n"
                + " | \\  / | __ _ _ __| | __\n"
                + " | |\\/| |/ _` | '__| |/ /\n"
                + " | |  | | (_| | |  |   < \n"
                + " |_|  |_|\\__,_|_|  |_|\\_\\\n";
        Ui.printDivider();
        Ui.println("Hello! I'm \n" + logo + "What can I do for you?");
        Ui.printDivider();
        Ui.print("\n");
    }
    
    public void bye() {
        Ui.println("Bye. Hope to see you again soon!");
        Ui.printDivider();
    }
}
