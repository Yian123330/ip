/**
 * Parses user input commands and creates the corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the full user command and returns the appropriate Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A Command object corresponding to the user's command.
     * @throws YiyiException If the command is invalid or cannot be parsed.
     */
    public static Command parse(String fullCommand) throws YiyiException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "todo":
            return new AddTodoCommand(arguments);
        case "deadline":
            return new AddDeadlineCommand(arguments);
        case "event":
            return new AddEventCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        default:
            throw new YiyiException("I'm sorry, but I don't know what that means.");
        }
    }
}