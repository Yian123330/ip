import java.util.Scanner;

public class Yiyi {
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        greet();
        echo();
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

    private static void echo(){
        String line;
        Scanner in = new Scanner(System.in);

        while(true) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(LINE);
            System.out.println(line);
            System.out.println(LINE);
        }
    }
}
