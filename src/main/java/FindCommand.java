import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String arguments) throws YiyiException {
        if (arguments.trim().isEmpty()) {
            throw new YiyiException("The keyword cannot be empty! Usage: find <keyword>");
        }
        this.keyword = arguments.trim().toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        // Search through all tasks for the keyword
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i + 1); // +1 because getTask uses 1-based index
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        // Display results
        ui.showFindResults(matchingTasks, keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}