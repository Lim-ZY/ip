package mark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import mark.task.Task;
import mark.task.TaskList;

/**
 * Handles user interactions and displays intended output.
 */
public class Ui {
    private static final PrintWriter PW = new PrintWriter(System.out);
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Prints divider line.
     */
    public static void printDivider() {
        Ui.PW.println("_____________________________________");
        Ui.PW.flush();
    }

    /**
     * Prints String s with a newline using PrintWriter.
     * PrintWriter println wrapper.
     *
     * @param s String.
     */
    public static void println(String s) {
        Ui.PW.println(s);
        Ui.PW.flush();
    }

    /**
     * Prints String s without a newline using PrintWriter.
     * PrintWriter print wrapper.
     *
     * @param s String.
     */
    public static void print(String s) {
        Ui.PW.print(s);
        Ui.PW.flush();
    }

    /**
     * Prints message of exception passed in using PrintWriter.
     *
     * @param e Exception.
     */
    public static void printException(Exception e) {
        Ui.PW.println(e.getMessage());
        Ui.printDivider();
        Ui.PW.flush();
    }

    /**
     * Reads a single line of input using BufferedReader.
     * Additionally prints a divider using Ui.printDivider().
     *
     * @return userInput String.
     * @throws IOException if readLine() fails.
     */
    public String readInput() throws IOException {
        String input = Ui.BR.readLine();
        Ui.printDivider();
        return input;
    }

    public static String getAllTasksMessage(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Prints task added into TaskList.
     * Additionally prints a divider using Ui.printDivider().
     * Length of the TaskList is needed to update user of no. of tasks in TaskList.
     *
     * @param t   Task object.
     * @param len int value.
     */
    public static void printAddedTask(Task t, int len) {
        Ui.println(getAddedTaskMessage(t, len));
        Ui.printDivider();
    }

    /**
     * Returns add task message.
     *
     * @param t
     * @param len
     */
    public static String getAddedTaskMessage(Task t, int len) {
        return "Got it. I've added this task:\n\t" + t.toString() + "\nNow you have " + len + " tasks in the list.";
    }

    /**
     * Prints task marked done in TaskList.
     * Additionally prints a divider using Ui.printDivider().
     *
     * @param t Task object.
     */
    public static void markDone(Task t) {
        Ui.println(getMarkDoneMessage(t));
        Ui.printDivider();
    }

    /**
     * Returns task marked done message.
     *
     * @param t Task object.
     */
    public static String getMarkDoneMessage(Task t) {
        return "Nice! I've marked this task as done:\n\t" + t.toString();
    }

    /**
     * Prints task marked undone in TaskList.
     * Additionally prints a divider using Ui.printDivider().
     *
     * @param t Task object.
     */
    public static void markUndone(Task t) {
        Ui.println(getMarkUndoneMessage(t));
        Ui.printDivider();
    }

    /**
     * Returns task marked undone string.
     *
     * @param t Task object.
     */
    public static String getMarkUndoneMessage(Task t) {
        return "OK, I've marked this task as not done yet:\n\t" + t.toString();
    }

    /**
     * Prints to be deleted task in TaskList.
     * Additionally prints a divider using Ui.printDivider().
     * Length of the TaskList is needed to update user of no. of tasks in TaskList.
     *
     * @param t   Task object.
     * @param len int value.
     */
    public static void printDeletedTask(Task t, int len) {
        Ui.println(getDeletedTaskMessage(t, len));
        Ui.printDivider();
    }

    /**
     * Returns deleted task string.
     *
     * @param t   Task object.
     * @param len int value.
     */
    public static String getDeletedTaskMessage(Task t, int len) {
        return "Noted. I've removed this task:\n\t" + t.toString() + "\nNow you have " + len + " tasks in the list.";
    }

    /**
     * Prints tasks found by FindCommand.
     * Additionally prints a divider using Ui.printDivider().
     *
     * @param tasks List of Tasks.
     */
    public static void printTasksFound(List<Task> tasks) {
        Ui.print(getTasksFoundMessage(tasks));
        Ui.printDivider();
    }

    /**
     * Returns tasks found as string.
     *
     * @param tasks List of Tasks.
     */
    public static String getTasksFoundMessage(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for (Task t : tasks) {
            sb.append(i++ + ". " + t.toString());
            sb.append("\n");
        }
        return "Here are the matching tasks in your list:\n" + sb.toString();
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
    public static void greet() {
        Ui.printDivider();
        Ui.println(getGreetingMessage());
        Ui.printDivider();
        Ui.print("\n");
    }

    /**
     * Returns greeting message as string.
     */
    public static String getGreetingMessage() {
        String logo = "  __  __            _    \n"
                + " |  \\/  |          | |   \n"
                + " | \\  / | __ _ _ __| | __\n"
                + " | |\\/| |/ _` | '__| |/ /\n"
                + " | |  | | (_| | |  |   < \n"
                + " |_|  |_|\\__,_|_|  |_|\\_\\\n";
        return "Hello! I'm \n" + logo + "\nWhat can I do for you?";
    }

    /**
     * Prints termination message.
     */
    public void bye() {
        Ui.println(getByeMessage());
        Ui.printDivider();
    }

    /**
     * Returns farewell message as string.
     */
    public static String getByeMessage() {
        return "Bye. Hope to see you again soon!";
    }
}
