import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Yiyi");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again!");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        showLine();
        System.out.println("OOPS!! " + message);
        showLine();
    }

    public void showTaskAdded(Task task, int size) {
        showLine();
        System.out.println("Added task: " + task);
        System.out.println("Now you have " + size + " tasks in your list.");
        showLine();
    }

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

    public void showTaskMarked(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        showLine();
    }

    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        showLine();
    }

    public void showTaskDeleted(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println("Could not load tasks from file. Starting with empty list.");
        showLine();
    }
}