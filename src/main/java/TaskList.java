import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTaskAsDone(int index) {
        getTask(index).markAsDone();
    }

    public void markTaskAsUndone(int index) {
        getTask(index).markAsUndone();
    }
}