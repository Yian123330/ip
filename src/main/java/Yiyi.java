import java.util.ArrayList;
import java.util.Scanner;

public class Yiyi {
    private static final String LINE = "____________________________________________________________";
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        try {
            tasks = Storage.loadTasks();
        } catch (Exception e) {
            tasks = new ArrayList<>();
            System.out.println("Could not load tasks from file. Starting with empty list.");
        }

        greet();
        handleUserInput();
        bye();
    }

    private static void greet(){
        System.out.println(LINE);
        System.out.println("Hello! I'm Yiyi");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void bye(){
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(LINE);
    }

    private static void handleUserInput(){
        Scanner in = new Scanner(System.in);

        while(true) {
            String line = in.nextLine().trim();
            try {
                if (line.equalsIgnoreCase("bye")) {
                    break;
                } else if (line.equalsIgnoreCase("list")) {
                    showTaskList();
                } else if (line.startsWith("mark ")) {
                    markTask(line);
                } else if (line.startsWith("unmark ")) {
                    unmarkTask(line);
                } else if (line.startsWith("delete ")) {    // FROM LEVEL-6: Delete feature
                    deleteTask(line);
                } else if (line.startsWith("todo")) {
                    addTodo(line);
                } else if (line.startsWith("deadline")) {
                    addDeadline(line);
                } else if (line.startsWith("event")) {
                    addEvent(line);
                } else {
                    throw new YiyiException("I'm sorry, but I don't know what that mean.");
                }

                Storage.saveTasks(tasks);

            } catch (YiyiException e) {
                printError(e.getMessage());
            } catch (Exception e) {
                printError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void printError(String errorMessage) {
        System.out.println(LINE);
        System.out.println("OOPS!! " + errorMessage);
        System.out.println(LINE);
    }

    private static void addTodo(String line) throws YiyiException{
        String description;
        if(line.equalsIgnoreCase("todo") || line.length() <= 4){
            description = "";
        }else{
            description = line.substring(4).trim();
        }
        if(description.isEmpty()){
            throw new YiyiException("The description of a Todo cannot be empty.");
        }

        Todo todo = new Todo(description);
        tasks.add(todo);
        System.out.println(LINE);
        System.out.println("Added Todo task: " + todo);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        System.out.println(LINE);
    }

    private static void addDeadline(String line) throws YiyiException{
        if(line.length() <= 8){
            throw new YiyiException("Please use the format: deadline <description> /by <date>");
        }

        String remaining = line.substring(9).trim();
        if(!remaining.contains("/by")){
            throw new YiyiException("Please use the format: deadline <description> /by <date>");
        }

        String[] parts = remaining.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()){
            throw new YiyiException("Description and deadline cannot be empty.");
        }
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        System.out.println(LINE);
        System.out.println("Added Deadline task: " + deadline);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        System.out.println(LINE);
    }

    private static void addEvent(String line) throws YiyiException{
        if(line.length() <= 5){
            throw new YiyiException("Please use the format: event <description> /from <start> /to <end>");
        }

        String remaining = line.substring(6).trim();
        if(!remaining.contains("/from") || !remaining.contains("/to")){
            throw new YiyiException("Please use the format: event <description> /from <start> /to <end>");
        }

        String[] parts = remaining.split("/from", 2);
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if(timeParts.length < 2){
            throw new YiyiException("Please use the format: event <description> /from <start> /to <end>");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()){
            throw new YiyiException("Description, start time and end time cannot be empty.");
        }
        Event event = new Event(description, from, to);
        tasks.add(event);
        System.out.println(LINE);
        System.out.println("Added Event task: " + event);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        System.out.println(LINE);
    }

    private static void showTaskList(){
        System.out.println(LINE);

        if(tasks.isEmpty()){
            System.out.println("Your task list is empty!");
        }else{
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < tasks.size(); i++){
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(LINE);
    }

    private static void markTask(String line) throws YiyiException{
        try {
            int taskNumber = Integer.parseInt(line.substring(5).trim());

            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println(LINE);
                System.out.println("Task number " + taskNumber + " does not exist!");
                System.out.println(LINE);
                return;
            }

            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();

            System.out.println(LINE);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("  " + task);
            System.out.println(LINE);
        } catch (NumberFormatException e){
            throw new YiyiException("Please enter a valid task number! Example: mark <task number>");
        }
    }

    private static void unmarkTask(String line) throws YiyiException{
        try {
            int taskNumber = Integer.parseInt(line.substring(7).trim());

            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println(LINE);
                System.out.println("Task number " + taskNumber + " does not exist!");
                System.out.println(LINE);
                return;
            }

            Task task = tasks.get(taskNumber - 1);
            task.markAsUndone();

            System.out.println(LINE);
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println("  " + task);
            System.out.println(LINE);
        } catch (NumberFormatException e){
            throw new YiyiException("Please enter a valid task number! Example: unmark <task number>");
        }
    }
    
    private static void deleteTask(String line) throws YiyiException {
        try {
            int taskNumber = Integer.parseInt(line.substring(7).trim());

            if(taskNumber < 1 || taskNumber > tasks.size()){
                System.out.println(LINE);
                System.out.println("Task number " + taskNumber + " does not exist!");
                System.out.println(LINE);
                return;
            }
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println(LINE);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE);
        } catch (NumberFormatException e){
            throw new YiyiException("Please enter a valid task number! Example: delete <task number>");
        }
    }
}