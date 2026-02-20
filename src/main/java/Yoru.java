import java.util.ArrayList;
import java.util.Scanner;
import yoru.Deadline;
import yoru.Event;
import yoru.Storage;
import yoru.Task;
import yoru.Todo;

/**
 * Main class for the Yoru chatbot application.
 * Manages task list operations and user interactions.
 */
public class Yoru {
    private static final String LINE_SEPARATOR = "_______________________________________";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark ";
    private static final String COMMAND_UNMARK = "unmark ";
    private static final String COMMAND_TODO = "todo ";
    private static final String COMMAND_DEADLINE = "deadline ";
    private static final String COMMAND_EVENT = "event ";
    private static final String COMMAND_DELETE = "delete ";

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        showWelcome();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = Storage.load();

        while (true) {
            try {
                String reply = scanner.nextLine();
                System.out.println(LINE_SEPARATOR);

                if (reply.equalsIgnoreCase(COMMAND_BYE)) {
                    showGoodbye();
                    scanner.close();
                    return;
                } else if (reply.equalsIgnoreCase(COMMAND_LIST)) {
                    showTaskList(tasks);
                } else if (reply.startsWith(COMMAND_MARK)) {
                    handleMarkTask(reply, tasks);
                    Storage.save(tasks);
                } else if (reply.startsWith(COMMAND_UNMARK)) {
                    handleUnmarkTask(reply, tasks);
                    Storage.save(tasks);
                } else if (reply.startsWith(COMMAND_TODO)) {
                    handleTodoTask(reply, tasks);
                    Storage.save(tasks);
                } else if (reply.startsWith(COMMAND_DEADLINE)) {
                    handleDeadlineTask(reply, tasks);
                    Storage.save(tasks);
                } else if (reply.startsWith(COMMAND_EVENT)) {
                    handleEventTask(reply, tasks);
                    Storage.save(tasks);
                } else if (reply.startsWith(COMMAND_DELETE)) {
                    handleDeleteTask(reply, tasks);
                    Storage.save(tasks);
                } else {
                    throw new YoruException("I don't understand that command.");
                }
            } catch (YoruException e) {
                System.out.println("     " + e.getMessage());
                System.out.println(LINE_SEPARATOR);
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid task number.");
                System.out.println(LINE_SEPARATOR);
            } 
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

    private static void showTaskList(ArrayList<Task> tasks) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleMarkTask(String reply, ArrayList<Task> tasks) {
        int taskIndex = Integer.parseInt(reply.substring(COMMAND_MARK.length())) - 1;
        tasks.get(taskIndex).markAsDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks.get(taskIndex));
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleUnmarkTask(String reply, ArrayList<Task> tasks) {
        int taskIndex = Integer.parseInt(reply.substring(COMMAND_UNMARK.length())) - 1;
        tasks.get(taskIndex).markAsNotDone();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + tasks.get(taskIndex));
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleTodoTask(String reply, ArrayList<Task> tasks) throws YoruException {
        String description = reply.substring(COMMAND_TODO.length());
        if (description.isEmpty()) {
            throw new YoruException("The description of a todo cannot be empty.");
        }
        tasks.add(new Todo(description));
        showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
    }

    private static void handleDeadlineTask(String reply, ArrayList<Task> tasks) throws YoruException {
        String args = reply.substring(COMMAND_DEADLINE.length());

        if (!args.contains(" /by ")) {
            throw new YoruException("Deadline format: deadline <description> /by <time>");
        }

        String[] parts = reply.substring(COMMAND_DEADLINE.length()).split(" /by ", 2);
        String description = parts[0];
        String by = parts[1];
        tasks.add(new Deadline(description, by));
        showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
    }

    private static void handleEventTask(String reply, ArrayList<Task> tasks) throws YoruException {
        String args = reply.substring(COMMAND_EVENT.length());

        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new YoruException("Event format: event <description> /from <start time> /to <end time>");
        }

        String[] parts = reply.substring(COMMAND_EVENT.length()).split(" /from ", 2);
        String description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        String from = timeParts[0];
        String to = timeParts[1];
        tasks.add(new Event(description, from, to));
        showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
    }

    private static void showTaskAdded(Task task, int taskCount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    private static void  handleDeleteTask(String reply, ArrayList<Task> tasks) throws YoruException {
        int taskIndex = Integer.parseInt(reply.substring(COMMAND_DELETE.length()).trim()) - 1;
        
        if (taskIndex < 0 || taskIndex >= tasks.size()) throw new YoruException("Invalid task number.");
        Task removed = tasks.remove(taskIndex);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + removed);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }
}