import java.util.Scanner;

public class Yiyi {
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int TaskCounts = 0;
    public static void main(String[] args) {
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
            if (line.equalsIgnoreCase("bye")) {
                break;
            }else if(line.equalsIgnoreCase("list")){
                showTaskList();
            }else if(line.startsWith("mark ")){
                markTask(line);
            }else if(line.startsWith("unmark ")){
                unmarkTask(line);
            }else if(line.startsWith("todo ")){
                addTodo(line);
            }else if(line.startsWith("deadline ")){
                addDeadline(line);
            }else if(line.startsWith("event ")){
                addEvent(line);
            }else{
                System.out.println(LINE);
                System.out.println("Unknown command!");
                System.out.println(LINE);
            }
        }
    }

    private static void addTodo(String line){
        if(TaskCounts >= MAX_TASKS){
            System.out.println(LINE);
            System.out.println("Sorry, I can only store up to 100 tasks!");
            System.out.println(LINE);
            return;
        }

        String description = line.substring(5).trim();
        if(description.isEmpty()){
            System.out.println(LINE);
            System.out.println("The description of a Todo cannot be empty.");
            System.out.println(LINE);
            return;
        }

        tasks[TaskCounts] = new Todo(description);
        TaskCounts++;
        System.out.println(LINE);
        System.out.println("added Todo task: " + tasks[TaskCounts - 1]);
        System.out.println("Now you have" + TaskCounts + "tasks in your list.");
        System.out.println(LINE);
    }

    private static void addDeadline(String line){
        if(TaskCounts >= MAX_TASKS){
            System.out.println(LINE);
            System.out.println("Sorry, I can only store up to 100 tasks!");
            System.out.println(LINE);
            return;
        }

        String remaining = line.substring(9).trim();
        if(!remaining.contains("/by")){
            System.out.println(LINE);
            System.out.println("Please use the format: deadline <description> /by <date>");
            System.out.println(LINE);
            return;
        }

        String[] parts = remaining.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()){
            System.out.println(LINE);
            System.out.println("Description and deadline cannot be empty.");
            System.out.println(LINE);
            return;
        }
        tasks[TaskCounts] = new Deadline(description, by);
        TaskCounts++;
        System.out.println(LINE);
        System.out.println("added Deadline task: " + tasks[TaskCounts - 1]);
        System.out.println("Now you have" + TaskCounts + "tasks in your list.");
        System.out.println(LINE);
    }

    private static void addEvent(String line){
        if(TaskCounts >= MAX_TASKS){
            System.out.println(LINE);
            System.out.println("Sorry, I can only store up to 100 tasks!");
            System.out.println(LINE);
            return;
        }

        String remaining = line.substring(6).trim();
        if(!remaining.contains("/from") || !remaining.contains("/to")){
            System.out.println(LINE);
            System.out.println("Please use the format: event <description> /from <start> /to <end>");
            System.out.println(LINE);
            return;
        }

        String[] parts = remaining.split("/from", 2);
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if(timeParts.length < 2){
            System.out.println(LINE);
            System.out.println("Please use the format: event <description> /from <start> /to <end>");
            System.out.println(LINE);
            return;
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();


        if (description.isEmpty() || from.isEmpty() || to.isEmpty()){
            System.out.println(LINE);
            System.out.println("Description, start time and end time cannot be empty.");
            System.out.println(LINE);
            return;
        }
        tasks[TaskCounts] = new Event(description, from, to);
        TaskCounts++;
        System.out.println(LINE);
        System.out.println("added Event task: " + tasks[TaskCounts - 1]);
        System.out.println("Now you have " + TaskCounts + " tasks in your list.");
        System.out.println(LINE);
    }

    private static void showTaskList(){
        System.out.println(LINE);

        if(TaskCounts == 0){
            System.out.println("Your task list is empty!");
        }else{
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < TaskCounts; i++){
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println(LINE);

    }

    private static void markTask(String line){
        try {
            int taskNumber = Integer.parseInt(line.substring(5).trim());

            if (taskNumber < 1 || taskNumber > TaskCounts) {
                System.out.println(LINE);
                System.out.println("Task number " + taskNumber + " does not exist!");
                System.out.println(LINE);
                return;
            }

            Task task = tasks[taskNumber - 1];
            task.markAsDone();

            System.out.println(LINE);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(" " + task);
            System.out.println(LINE);
        } catch (NumberFormatException e){
            System.out.println(LINE);
            System.out.println("Please enter a valid task number!");
            System.out.println("Example: mark <task number>");
            System.out.println(LINE);
        }
    }

    private static void unmarkTask(String line){
        try{
            int taskNumber = Integer.parseInt(line.substring(7).trim());

            if (taskNumber < 1 || taskNumber > TaskCounts) {
                System.out.println(LINE);
                System.out.println("Task number " + taskNumber + " does not exist!");
                System.out.println(LINE);
                return;
            }

            Task task = tasks[taskNumber - 1];
            task.markAsUndone();

            System.out.println(LINE);
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(" " + task);
            System.out.println(LINE);
        } catch (NumberFormatException e){
            System.out.println(LINE);
            System.out.println("Please enter a valid task number!");
            System.out.println("Example: mark <task number>");
            System.out.println(LINE);
        }
    }
}
