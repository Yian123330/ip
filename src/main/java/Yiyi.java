import java.util.Scanner;

public class Yiyi {
    private static final String LINE = "____________________________________________________________";
    private static Task[] tasks = new Task[100];
    private static int counts = 0;
    public static void main(String[] args) {
        greet();
        inputTask();
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

    private static void inputTask(){
        Scanner in = new Scanner(System.in);

        while(true) {
            String line = in.nextLine().trim();
            if (line.equalsIgnoreCase("bye")) {
                break;
            }else if(line.equalsIgnoreCase("list")){
                showList();
            }else if(line.startsWith("mark ")){
                markTask(line);
            }else if(line.startsWith("unmark ")){
                unmarkTask(line);
            }else{
                addTask(line);
                }
            }
        }

    private static void addTask(String Description){
        if(counts >= 100){
            System.out.println(LINE);
            System.out.println("Sorry, I can only store up to 100 tasks!");
            System.out.println(LINE);
            return;
        }

        tasks[counts] = new Task(Description);
        counts++;
        System.out.println(LINE);
        System.out.println("added: " + Description);
        System.out.println(LINE);
    }

    private static void showList(){
        System.out.println(LINE);

        if(counts == 0){
            System.out.println("Your task list is empty!");
        }else{
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < counts; i++){
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println(LINE);

    }

    private static void markTask(String line){
        try {
            int taskNumber = Integer.parseInt(line.substring(5).trim());

            if (taskNumber < 1 || taskNumber > counts) {
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

            if (taskNumber < 1 || taskNumber > counts) {
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
