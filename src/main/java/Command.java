/**
 * Represents an executable command in the Yiyi chatbot application.
 * All specific commands should inherit from this abstract class.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI to interact with the user.
     * @param storage The storage to save/load tasks.
     * @throws YiyiException If there is an error during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws YiyiException;

    /**
     * Checks if this command is an exit command.
     *
     * @return true if this command exits the application, false otherwise.
     */
    public abstract boolean isExit();
}