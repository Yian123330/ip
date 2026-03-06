import java.util.ArrayList;

/**
 * Manages the list of tasks in the Yiyi chatbot application.
 * Provides operations to add, delete, retrieve, and modify tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     * Uses 1-based indexing for user-friendliness.
     *
     * @param index The 1-based index of the task to delete.
     * @return The task that was removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Retrieves a task from the list at the specified index.
     * Uses 1-based indexing for user-friendliness.
     *
     * @param index The 1-based index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the entire ArrayList of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a task as done at the specified index.
     * Uses 1-based indexing for user-friendliness.
     *
     * @param index The 1-based index of the task to mark as done.
     */
    public void markTaskAsDone(int index) {
        getTask(index).markAsDone();
    }

    /**
     * Marks a task as not done at the specified index.
     * Uses 1-based indexing for user-friendliness.
     *
     * @param index The 1-based index of the task to mark as not done.
     */
    public void markTaskAsUndone(int index) {
        getTask(index).markAsUndone();
    }
}