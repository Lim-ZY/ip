package mark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import mark.command.UpdateCommand;

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
    private static final String INVALID_DEADLINE_ERROR = "Usage: deadline <taskName> /by <YYYY-MM-DD> <HHMM>";
    private static final String INVALID_EVENT_ERROR = "Usage: event <taskName> /from <YYYY-MM-DD> <HHMM> "
            + "/to <YYYY-MM-DD> <HHMM>";
    private static final String INVALID_UPDATE_ERROR = "Usage: update <taskID> <taskFieldName> <taskFieldValue> ...";
    private static final int COMMAND_MAIN_SEGMENTS = 2;
    private static final int MAX_FIELDS = 3;
    private static final Pattern UPDATE_PATTERN = Pattern.compile("/([a-zA-Z]+)\\s+([^/]+)");

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
            if (segments.length != 2 || segments[1].isBlank()) {
                throw new InvalidFormatException("Usage: todo <task>");
            }
            return new TodoCommand(segments[1]);
        case "deadline":
            return Parser.parseDeadline(segments);
        case "event":
            return Parser.parseEvent(segments);
        case "find":
            if (segments.length != 2 || segments[1].isBlank()) {
                throw new InvalidFormatException("Usage: find <keyword>");
            }
            return new FindCommand(segments[1]);
        case "update":
            return Parser.parseUpdate(segments);
        default:
            return new UnknownCommand();
        }
    }

    private static void validateSegments(String[] segments, String errorMessage) throws InvalidFormatException {
        if (segments.length != COMMAND_MAIN_SEGMENTS || segments[1].isBlank()) {
            throw new InvalidFormatException(errorMessage);
        }
    }

    private static int getTaskId(String[] idAndUpdates) throws InvalidFormatException {
        int taskId = Integer.parseInt(idAndUpdates[0]) - 1;
        if (idAndUpdates.length != 2 || idAndUpdates[1].isBlank()) {
            throw new InvalidFormatException(INVALID_UPDATE_ERROR);
        }
        return taskId;
    }

    private static String getUpdates(String[] idAndUpdates) {
        return idAndUpdates[1].trim();
    }

    private static Map<String, String> getFieldValuePairs(String updates) throws InvalidFormatException {
        Map<String, String> fieldValuePairs = new HashMap<>();
        Matcher updateMatcher = UPDATE_PATTERN.matcher(updates);
        while (updateMatcher.find()) {
            String fieldName = updateMatcher.group(1);
            String fieldValue = updateMatcher.group(2).trim();
            if (fieldValue.isEmpty()) {
                throw new InvalidFormatException(INVALID_UPDATE_ERROR);
            }
            fieldValuePairs.put(fieldName, fieldValue);
        }
        if (fieldValuePairs.isEmpty()) {
            throw new InvalidFormatException(INVALID_UPDATE_ERROR);
        }
        return fieldValuePairs;
    }

    private static String getArgument(String input, String flag, String nextFlag) throws InvalidFormatException {
        int start = input.indexOf(flag);
        if (start == -1) {
            throw new InvalidFormatException("Missing " + flag);
        }

        start += flag.length();
        int end = (nextFlag != null) ? input.indexOf(nextFlag) : input.length();

        if (end == -1 || end < start) {
            throw new InvalidFormatException("Invalid format for " + nextFlag);
        }

        String result = input.substring(start, end).trim();
        if (result.isEmpty()) {
            throw new InvalidFormatException("Empty value for " + flag);
        }

        return result;
    }

    private static Command parseUpdate(String[] segments) throws InvalidFormatException {
        validateSegments(segments, INVALID_UPDATE_ERROR);

        String[] idAndUpdates = segments[1].trim().split(" ", 2);
        int taskID = getTaskId(idAndUpdates);
        String updates = getUpdates(idAndUpdates);
        Map<String, String> fieldValuePairs = getFieldValuePairs(updates);

        return new UpdateCommand(taskID, fieldValuePairs);
    }

    /**
     * Returns executable DeadlineCommand upon further parsing of input segments.
     *
     * @param segments String[] of user input.
     * @return DeadlineCommand.
     * @throws InvalidFormatException when commands are invalid.
     */
    private static Command parseDeadline(String[] segments) throws InvalidFormatException {
        validateSegments(segments, INVALID_DEADLINE_ERROR);
        String input = segments[1];

        String flag = "/by ";
        String byString = getArgument(input, flag, null);
        String taskName = input.substring(0, input.indexOf(flag)).trim();
        if (taskName.isEmpty()) {
            throw new InvalidFormatException(INVALID_DEADLINE_ERROR);
        }

        LocalDateTime date;
        try {
            date = LocalDateTime.parse(byString, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(INVALID_DEADLINE_ERROR);
        }
        return new DeadlineCommand(taskName, date);
    }

    /**
     * Returns executable EventCommand upon further parsing of input segments.
     *
     * @param segments String[] of user input.
     * @return EventCommand.
     * @throws InvalidFormatException when commands are invalid.
     */
    private static Command parseEvent(String[] segments) throws InvalidFormatException {
        validateSegments(segments, INVALID_EVENT_ERROR);
        String input = segments[1];

        String flagFrom = "/from ";
        String flagTo = "/to ";

        String fromString = getArgument(input, flagFrom, flagTo);
        String toString = getArgument(input, flagTo, null);
        String taskName = segments[1].substring(0, input.indexOf(flagFrom)).trim();

        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = LocalDateTime.parse(fromString, OUTPUT_DATETIME_FORMAT);
            toDate = LocalDateTime.parse(toString, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(INVALID_EVENT_ERROR);
        }
        return new EventCommand(taskName, fromDate, toDate);
    }
}
