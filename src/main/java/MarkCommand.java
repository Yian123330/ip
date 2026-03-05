public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String arguments) throws YiyiException {
        try {
            this.index = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new YiyiException("Please enter a valid task number! Example: mark <task number>");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YiyiException {
        if (index < 1 || index > tasks.size()) {
            throw new YiyiException("Task number " + index + " does not exist!");
        }
        tasks.markTaskAsDone(index);
        ui.showTaskMarked(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}