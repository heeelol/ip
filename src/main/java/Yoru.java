import java.util.Scanner;

public class Yoru {
    public static void main(String[] args) {
        String line = "_______________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Yoru");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int taskCount = 0;

        while (true) {
            String reply = scanner.nextLine();
            System.out.println(line);

            if (reply.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            } else if (reply.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                System.out.println(line);
            } else {
                list[taskCount] = reply;
                taskCount++;
                System.out.println("added: " + reply);
                System.out.println(line);
            }
        }
    }
}