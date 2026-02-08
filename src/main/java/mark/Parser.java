package mark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mark.command.ByeCommand;
import mark.command.Command;
import mark.command.DeadlineCommand;
import mark.command.DeleteCommand;
import mark.command.EventCommand;
import mark.command.FindCommand;
import mark.command.ListCommand;
import mark.command.MarkCommand;
import mark.command.TodoCommand;
import mark.command.UnknownCommand;
import mark.command.UnmarkCommand;

/**
 * Parses user input into Command objects.
 */
public class Parser {
    /**
     * Input format of date and time
     **/
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Returns executable Command subclasses.
     *
     * @param input User Input.
     * @return Command subclass.
     * @throws InvalidFormatException when commands are invalid.
     */
    public static Command parse(String input) throws InvalidFormatException {
        assert input != null : "argument to parse should be a valid String";

        String[] segments = input.trim().split(" ", 2);
        String action = segments[0];

        switch (action) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "mark":
            if (segments.length != 2 || segments[1].isBlank()) {
                throw new InvalidFormatException("Usage: mark <index>");
            }
            return new MarkCommand(Integer.parseInt(segments[1]) - 1);
        case "unmark":
            if (segments.length != 2 || segments[1].isBlank()) {
                throw new InvalidFormatException("Usage: unmark <index>");
            }
            return new UnmarkCommand(Integer.parseInt(segments[1]) - 1);
        case "delete":
            if (segments.length != 2 || segments[1].isBlank()) {
                throw new InvalidFormatException("Usage: delete <index>");
            }
            return new DeleteCommand(Integer.parseInt(segments[1]) - 1);
        case "todo":
            return new TodoCommand(segments);
        case "deadline":
            return Parser.parseDeadline(segments);
        case "event":
            return Parser.parseEvent(segments);
        case "find":
            return new FindCommand(segments);
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Returns executable DeadlineCommand upon further parsing of input segments.
     *
     * @param segments String[] of user input.
     * @return DeadlineCommand.
     * @throws InvalidFormatException when commands are invalid.
     */
    private static Command parseDeadline(String[] segments) throws InvalidFormatException {
        if (segments.length < 2 || segments[1].isBlank()) {
            throw new InvalidFormatException("Usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
        }
        int byIndex = segments[1].indexOf("/by");
        if (byIndex == -1) {
            throw new InvalidFormatException("Usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
        }

        String deadline = segments[1].substring(byIndex + 4);
        String taskName = segments[1].substring(0, byIndex).trim();
        if (deadline.isEmpty() || taskName.isEmpty()) {
            throw new InvalidFormatException("Usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
        }

        LocalDateTime date;
        try {
            date = LocalDateTime.parse(deadline, OUTPUT_DATETIME_FORMAT);
            return new DeadlineCommand(taskName, date);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
        }
    }

    /**
     * Returns executable EventCommand upon further parsing of input segments.
     *
     * @param segments String[] of user input.
     * @return EventCommand.
     * @throws InvalidFormatException when commands are invalid.
     */
    private static Command parseEvent(String[] segments) throws InvalidFormatException {
        if (segments.length < 2 || segments[1].isBlank()) {
            throw new InvalidFormatException("usage: event <task> /from <YYYY-MM-DD> <HHMM> "
                    + "/to <YYYY-MM-DD> <HHMM>");
        }
        int fromIndex = segments[1].indexOf("/from");
        int toIndex = segments[1].indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new InvalidFormatException("usage: event <task> /from <YYYY-MM-DD> <HHMM> "
                    + "/to <YYYY-MM-DD> <HHMM>");
        }

        String from = segments[1].substring(fromIndex + 6, toIndex).trim();
        String to = segments[1].substring(toIndex + 4).trim();
        String taskName = segments[1].substring(0, fromIndex).trim();
        if (from.isEmpty() || to.isEmpty() || taskName.isEmpty()) {
            throw new InvalidFormatException("Usage: event <task> /from <YYYY-MM-DD> <HHMM> "
                    + "/to <YYYY-MM-DD> <HHMM>");
        }

        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = LocalDateTime.parse(from, OUTPUT_DATETIME_FORMAT);
            toDate = LocalDateTime.parse(to, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("usage: event <task> /from <YYYY-MM-DD> <HHMM> "
                    + "/to <YYYY-MM-DD> <HHMM>");
        }
        return new EventCommand(taskName, fromDate, toDate);
    }
}
