public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws YiyiException;
    public abstract boolean isExit();
}