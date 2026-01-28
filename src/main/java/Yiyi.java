import java.util.Scanner;

public class Yiyi {
    private static final String LINE = "____________________________________________________________";
    private static String[] tasks = new String[100];
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
        String line;
        Scanner in = new Scanner(System.in);

        while(true) {
            line = in.nextLine().trim();
            if (line.equalsIgnoreCase("bye")) {
                break;
            }else if(line.equalsIgnoreCase("list")){
                showList();
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

        tasks[counts] = Description;
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
            for(int i = 0; i < counts; i++){
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println(LINE);

    }
}
