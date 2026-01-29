package mark;

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
    
    public String readInput() throws IOException {
        String input = Ui.br.readLine();
        Ui.printDivider();
        return input;
    }
    
    public static void printAddedTask(Task t, int len) {
        Ui.println("Got it. I've added this task:\n\t" + t.toString());
        Ui.println("Now you have " + len + " tasks in the list.");
        Ui.printDivider();
    }
    
    public static void markDone(Task t) {
        Ui.println("Nice! I've marked this task as done:\n\t" + t.toString());
        Ui.printDivider();
    }
    
    public static void markUndone(Task t) {
        Ui.println("OK, I've marked this task as not done yet:\n\t" + t.toString());
        Ui.printDivider();
    }
    
    public static void printDeletedTask(Task t, int len) {
        Ui.println("Noted. I've removed this task:\n\t" + t.toString());
        Ui.println("Now you have " + len + " tasks in the list.");
        Ui.printDivider();
    }
    
    public void showLoadingError() {
        Ui.println("Could not read/create file. Continuing with empty tasklist.");
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
