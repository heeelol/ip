import java.util.Scanner;

/**
 * Main class for the Yoru chatbot application.
 * Manages task list operations and user interactions.
 */
public class Yoru {
    private static final String LINE_SEPARATOR = "_______________________________________";
    private static final int MAX_TASKS = 100;
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark ";
    private static final String COMMAND_UNMARK = "unmark ";
    private static final String COMMAND_TODO = "todo ";
    private static final String COMMAND_DEADLINE = "deadline ";
    private static final String COMMAND_EVENT = "event ";

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        showWelcome();

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        while (true) {
            try {
                String reply = scanner.nextLine();
                System.out.println(LINE_SEPARATOR);

                if (reply.equalsIgnoreCase(COMMAND_BYE)) {
                    showGoodbye();
                    scanner.close();
                    return;
                } else if (reply.equalsIgnoreCase(COMMAND_LIST)) {
                    showTaskList(tasks, taskCount);
                } else if (reply.startsWith(COMMAND_MARK)) {
                    handleMarkTask(reply, tasks);
                } else if (reply.startsWith(COMMAND_UNMARK)) {
                    handleUnmarkTask(reply, tasks);
                } else if (reply.startsWith(COMMAND_TODO)) {
                    taskCount = handleTodoTask(reply, tasks, taskCount);
                } else if (reply.startsWith(COMMAND_DEADLINE)) {
                    taskCount = handleDeadlineTask(reply, tasks, taskCount);
                } else if (reply.startsWith(COMMAND_EVENT)) {
                    taskCount = handleEventTask(reply, tasks, taskCount);
                } else {
                    throw new YoruException("I don't understand that command.");
                }
            } catch (YoruException e) {
                System.out.println("     " + e.getMessage());
                System.out.println(LINE_SEPARATOR);
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid task number.");
                System.out.println(LINE_SEPARATOR);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Task list is full. Cannot add more tasks.");
                System.out.println(LINE_SEPARATOR);
            }
        }
    }

    private static void checkTaskListFull(int taskCount) throws YoruException {
    if (taskCount >= MAX_TASKS) {
        throw new YoruException("Task list is full. Cannot add more tasks.");
    }
}

    private static void showWelcome() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("     Hello! I'm Yoru");
        System.out.println("     What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    private static void showGoodbye() {
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    private static void showTaskList(Task[] tasks, int taskCount) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleMarkTask(String reply, Task[] tasks) {
        int taskIndex = Integer.parseInt(reply.substring(COMMAND_MARK.length())) - 1;
        tasks[taskIndex].markAsDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks[taskIndex]);
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleUnmarkTask(String reply, Task[] tasks) {
        int taskIndex = Integer.parseInt(reply.substring(COMMAND_UNMARK.length())) - 1;
        tasks[taskIndex].markAsNotDone();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + tasks[taskIndex]);
        System.out.println(LINE_SEPARATOR);
    }

    private static int handleTodoTask(String reply, Task[] tasks, int taskCount) throws YoruException {
        checkTaskListFull(taskCount);
        String description = reply.substring(COMMAND_TODO.length());
        if (description.isEmpty()) {
            throw new YoruException("The description of a todo cannot be empty.");
        }
        tasks[taskCount] = new Todo(description);
        taskCount++;
        showTaskAdded(tasks[taskCount - 1], taskCount);
        return taskCount;
    }

    private static int handleDeadlineTask(String reply, Task[] tasks, int taskCount) throws YoruException {
        checkTaskListFull(taskCount);
        String args = reply.substring(COMMAND_DEADLINE.length());

        if (!args.contains(" /by ")) {
            throw new YoruException("Deadline format: deadline <description> /by <time>");
        }

        String[] parts = reply.substring(COMMAND_DEADLINE.length()).split(" /by ", 2);
        String description = parts[0];
        String by = parts[1];
        tasks[taskCount] = new Deadline(description, by);
        taskCount++;
        showTaskAdded(tasks[taskCount - 1], taskCount);
        return taskCount;
    }

    private static int handleEventTask(String reply, Task[] tasks, int taskCount) throws YoruException {
        checkTaskListFull(taskCount);
        String args = reply.substring(COMMAND_EVENT.length());

        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new YoruException("Event format: event <description> /from <start time> /to <end time>");
        }

        String[] parts = reply.substring(COMMAND_EVENT.length()).split(" /from ", 2);
        String description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        String from = timeParts[0];
        String to = timeParts[1];
        tasks[taskCount] = new Event(description, from, to);
        taskCount++;
        showTaskAdded(tasks[taskCount - 1], taskCount);
        return taskCount;
    }

    private static void showTaskAdded(Task task, int taskCount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }
}