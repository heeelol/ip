import java.util.Scanner;

public class Yoru {
    public static void main(String[] args) {
        String line = "_______________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Yoru");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
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
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
            } else if (reply.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(reply.substring(5)) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks[taskIndex]);
                System.out.println(line);
            } else if (reply.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(reply.substring(7)) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks[taskIndex]);
                System.out.println(line);
            } else {
                tasks[taskCount] = new Task(reply);
                taskCount++;
                System.out.println("added: " + reply);
                System.out.println(line);
            }
        }
    }
}