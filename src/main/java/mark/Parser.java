package mark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /** Input format of date and time **/
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Returns executable Command subclasses.
     * 
     * @param input User Input
     * @return Command subclass
     * @throws InvalidFormatException
     */
    public static Command parse(String input) throws InvalidFormatException {
        String[] segments = input.trim().split(" ", 2);
        String action = segments[0];
        
        switch (action) {
        case "list": 
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(segments[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(segments[1]) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(segments[1]) - 1);
        case "todo":
            return new TodoCommand(segments);
        case "deadline":
            return Parser.parseDeadline(segments);
        case "event":
            return Parser.parseEvent(segments);
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Returns executable DeadlineCommand upon further parsing of input segments.
     * 
     * @param segments String[] of user input
     * @return DeadlineCommand
     * @throws InvalidFormatException
     */
    public static Command parseDeadline(String[] segments) throws InvalidFormatException {
        if (!segments[1].contains("/by")) {
            throw new InvalidFormatException("Usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
        }
        String deadline = segments[1].substring(segments[1].indexOf("/by") + 4);
        String taskName = segments[1].substring(0, segments[1].indexOf("/by")).trim();
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(deadline, dtf);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Usage: deadline <task> /by <YYYY-MM-DD> <HHMM>");
        }
        return new DeadlineCommand(taskName, date);
    }

    /**
     * Returns executable EventCommand upon further parsing of input segments.
     *
     * @param segments String[] of user input
     * @return EventCommand
     * @throws InvalidFormatException
     */
    public static Command parseEvent(String[] segments) throws InvalidFormatException {
        if (!segments[1].contains("/from") || !segments[1].contains("/to")) {
            throw new InvalidFormatException("usage: event <task> /from <YYYY-MM-DD> <HHMM> "
                    + "/to <YYYY-MM-DD> <HHMM>");
        }
        LocalDateTime fromDate, toDate;
        try {
            fromDate = LocalDateTime.parse(segments[1].substring(segments[1].indexOf("/from") + 6,
                    segments[1].indexOf("/to")).trim(), dtf);
            toDate = LocalDateTime.parse(segments[1].substring(segments[1].indexOf("/to") + 4), dtf);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("usage: event <task> /from <YYYY-MM-DD> <HHMM> "
                    + "/to <YYYY-MM-DD> <HHMM>");
        }
        String taskName = segments[1].substring(0, segments[1].indexOf("/from")).trim();
        return new EventCommand(taskName, fromDate, toDate);
    }
}
