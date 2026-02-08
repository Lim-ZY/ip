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
    private static final String INVALID_MARK_ERROR = "Usage: mark <index>";
    private static final String INVALID_UNMARK_ERROR = "Usage: unmark <index>";
    private static final String INVALID_DELETE_ERROR = "Usage: delete <index>";
    private static final String INVALID_DEADLINE_ERROR = "Usage: deadline <task> /by <YYYY-MM-DD> <HHMM>";
    private static final String INVALID_EVENT_ERROR = "Usage: event <task> /from <YYYY-MM-DD> <HHMM> "
            + "/to <YYYY-MM-DD> <HHMM>";
    private static final int COMMAND_MAIN_SEGMENTS = 2;

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
            if (segments.length != COMMAND_MAIN_SEGMENTS || segments[1].isBlank()) {
                throw new InvalidFormatException(INVALID_MARK_ERROR);
            }
            return new MarkCommand(Integer.parseInt(segments[1]) - 1);
        case "unmark":
            if (segments.length != COMMAND_MAIN_SEGMENTS || segments[1].isBlank()) {
                throw new InvalidFormatException(INVALID_UNMARK_ERROR);
            }
            return new UnmarkCommand(Integer.parseInt(segments[1]) - 1);
        case "delete":
            if (segments.length != COMMAND_MAIN_SEGMENTS || segments[1].isBlank()) {
                throw new InvalidFormatException(INVALID_DELETE_ERROR);
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
        if (segments.length < COMMAND_MAIN_SEGMENTS || segments[1].isBlank()) {
            throw new InvalidFormatException(INVALID_DEADLINE_ERROR);
        }
        int byIndex = segments[1].indexOf("/by");
        if (byIndex == -1) {
            throw new InvalidFormatException("Usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
        }

        String option = "/by ";
        int optionIndex = segments[1].indexOf(option);
        if (optionIndex == -1) {
            throw new InvalidFormatException(INVALID_DEADLINE_ERROR);
        }

        String deadline = segments[1].substring(optionIndex + option.length());
        String taskName = segments[1].substring(0, optionIndex).trim();
        if (deadline.isEmpty() || taskName.isEmpty()) {
            throw new InvalidFormatException(INVALID_DEADLINE_ERROR);
        }

        LocalDateTime date;
        try {
            date = LocalDateTime.parse(deadline, OUTPUT_DATETIME_FORMAT);
            return new DeadlineCommand(taskName, date);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(INVALID_DEADLINE_ERROR);
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
        if (segments.length < COMMAND_MAIN_SEGMENTS || segments[1].isBlank()) {
            throw new InvalidFormatException(INVALID_EVENT_ERROR);
        }
        String optionFrom = "/from ";
        String optionTo = "/to ";
        int fromIndex = segments[1].indexOf(optionFrom);
        int toIndex = segments[1].indexOf(optionTo);
        if (fromIndex == -1 || toIndex == -1) {
            throw new InvalidFormatException(INVALID_EVENT_ERROR);
        }

        String from = segments[1].substring(fromIndex + optionFrom.length(), toIndex).trim();
        String to = segments[1].substring(toIndex + optionTo.length()).trim();
        String taskName = segments[1].substring(0, fromIndex).trim();
        if (from.isEmpty() || to.isEmpty() || taskName.isEmpty()) {
            throw new InvalidFormatException(INVALID_EVENT_ERROR);
        }

        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = LocalDateTime.parse(from, OUTPUT_DATETIME_FORMAT);
            toDate = LocalDateTime.parse(to, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(INVALID_EVENT_ERROR);
        }
        return new EventCommand(taskName, fromDate, toDate);
    }
}
