import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles all user interface interactions for the Yiyi chatbot.
 * Responsible for displaying messages to the user and reading user input.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Yiyi");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message when the chatbot exits.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again!");
        showLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return The trimmed command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println("OOPS!! " + message);
        showLine();
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        showLine();
        System.out.println("Added task: " + task);
        System.out.println("Now you have " + size + " tasks in your list.");
        showLine();
    }

    /**
     * Displays the list of all tasks.
     *
     * @param tasks The ArrayList of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        showLine();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        showLine();
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        showLine();
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list after deletion.
     */
    public void showTaskDeleted(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message when there is an error loading tasks from the storage file.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("Could not load tasks from file. Starting with empty list.");
        showLine();
    }

    /**
     * Displays the results of a find operation.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     * @param keyword The keyword that was searched for.
     */
    public void showFindResults(ArrayList<Task> matchingTasks, String keyword) {
        showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No tasks found matching \"" + keyword + "\".");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        showLine();
    }
}