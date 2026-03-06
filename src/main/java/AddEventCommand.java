public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String arguments) throws YiyiException {
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new YiyiException("Please use the format: event <description> /from <start> /to <end>");
        }
        String[] parts = arguments.split("/from", 2);
        this.description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2) {
            throw new YiyiException("Please use the format: event <description> /from <start> /to <end>");
        }
        this.from = timeParts[0].trim();
        this.to = timeParts[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new YiyiException("Description, start time and end time cannot be empty!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YiyiException {
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        ui.showTaskAdded(event, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}