public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String arguments) throws YiyiException {
        if (!arguments.contains("/by")) {
            throw new YiyiException("Please use the format: deadline <description> /by <date>");
        }
        String[] parts = arguments.split("/by", 2);
        this.description = parts[0].trim();
        this.by = parts[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new YiyiException("Description and deadline cannot be empty!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YiyiException {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showTaskAdded(deadline, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}