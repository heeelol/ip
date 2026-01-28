import java.util.Scanner;

/**
 * Main class for the Yoru chatbot application.
 * Manages task list operations and user interactions.
 */
public class Yoru {
    private static final String LINE_SEPARATOR = "_______________________________________";
    private static final int MAX_TASKS = 100;

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm Yoru");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        while (true) {
            String reply = scanner.nextLine();
            System.out.println(LINE_SEPARATOR);

            if (reply.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE_SEPARATOR);
                scanner.close();
                return;
            } else if (reply.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(LINE_SEPARATOR);
            } else if (reply.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(reply.substring(5)) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks[taskIndex]);
                System.out.println(LINE_SEPARATOR);
            } else if (reply.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(reply.substring(7)) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks[taskIndex]);
                System.out.println(LINE_SEPARATOR);
            } else {
                tasks[taskCount] = new Task(reply);
                taskCount++;
                System.out.println("added: " + reply);
                System.out.println(LINE_SEPARATOR);
            }
        }
    }
}