public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String arguments) throws YiyiException {
        try {
            this.index = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new YiyiException("Please enter a valid task number! Example: delete <task number>");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YiyiException {
        if (index < 1 || index > tasks.size()) {
            throw new YiyiException("Task number " + index + " does not exist!");
        }
        Task removedTask = tasks.deleteTask(index);
        ui.showTaskDeleted(removedTask, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}