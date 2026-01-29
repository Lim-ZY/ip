package mark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class Ui {
    private static final PrintWriter pw =  new PrintWriter(System.out);
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Prints divider line.
     */
    public static void printDivider() {
        Ui.pw.println("_____________________________________");
        Ui.pw.flush();
    }

    /**
     * Prints String s with a newline using PrintWriter.
     * PrintWriter println wrapper.
     * 
     * @param s String.
     */
    public static void println(String s) {
        Ui.pw.println(s);
        Ui.pw.flush();
    }

    /**
     * Prints String s without a newline using PrintWriter.
     * PrintWriter print wrapper.
     *
     * @param s String.
     */
    public static void print(String s) {
        Ui.pw.print(s);
        Ui.pw.flush();
    }

    /**
     * Prints message of exception passed in using PrintWriter.
     *
     * @param e Exception.
     */
    public static void printException(Exception e) {
        Ui.pw.println(e.getMessage());
        Ui.printDivider();
        Ui.pw.flush();
    }

    /**
     * Reads a single line of input using BufferedReader.
     * Additionally prints a divider using Ui.printDivider().
     * 
     * @return userInput String.
     * @throws IOException if readLine() fails.
     */
    public String readInput() throws IOException {
        String input = Ui.br.readLine();
        Ui.printDivider();
        return input;
    }

    /**
     * Prints task added into TaskList.
     * Additionally prints a divider using Ui.printDivider().
     * Length of the TaskList is needed to update user of no. of tasks in TaskList.
     * 
     * @param t Task object.
     * @param len int value.
     */
    public static void printAddedTask(Task t, int len) {
        Ui.println("Got it. I've added this task:\n\t" + t.toString());
        Ui.println("Now you have " + len + " tasks in the list.");
        Ui.printDivider();
    }

    /**
     * Prints task marked done in TaskList.
     * Additionally prints a divider using Ui.printDivider().
     * 
     * @param t Task object.
     */
    public static void markDone(Task t) {
        Ui.println("Nice! I've marked this task as done:\n\t" + t.toString());
        Ui.printDivider();
    }

    /**
     * Prints task marked undone in TaskList.
     * Additionally prints a divider using Ui.printDivider().
     * 
     * @param t Task object.
     */
    public static void markUndone(Task t) {
        Ui.println("OK, I've marked this task as not done yet:\n\t" + t.toString());
        Ui.printDivider();
    }

    /**
     * Prints to be deleted task in TaskList.
     * Additionally prints a divider using Ui.printDivider().
     * Length of the TaskList is needed to update user of no. of tasks in TaskList.
     * 
     * @param t Task object.
     * @param len int value.
     */
    public static void printDeletedTask(Task t, int len) {
        Ui.println("Noted. I've removed this task:\n\t" + t.toString());
        Ui.println("Now you have " + len + " tasks in the list.");
        Ui.printDivider();
    }

    public static void printTasksFound(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for (Task t: tasks) {
            sb.append(i++ + ". " + t.toString());
            sb.append("\n");
        }
        Ui.print("Here are the matching tasks in your list:\n" + sb.toString());
        Ui.printDivider();
    }
    
    /**
     * Prints error message if data file could not be loaded.
     */
    public void showLoadingError() {
        Ui.println("Could not read/create file. Continuing with empty tasklist.");
    }

    /**
     * Prints greeting message.
     */
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

    /**
     * Prints termination message.
     */
    public void bye() {
        Ui.println("Bye. Hope to see you again soon!");
        Ui.printDivider();
    }
}
