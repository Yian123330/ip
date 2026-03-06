public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String arguments) throws YiyiException {
        this.description = arguments.trim();
        if (description.isEmpty()) {
            throw new YiyiException("The description of a Todo cannot be empty!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YiyiException {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.showTaskAdded(todo, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}