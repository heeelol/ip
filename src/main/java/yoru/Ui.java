package yoru;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user input/output interactions in the command-line interface.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "_______________________________________";
    private final Scanner scanner;

    /**
     * Creates a UI instance that reads from standard input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads one command line from the user.
     *
     * @return Raw user command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome banner and startup message.
     */
    public void showWelcome() {
        String logo =  """
                        _        _          _            _      _               \r
                       /\\ \\     /\\_\\       /\\ \\         /\\ \\   /\\_\\             \r
                       \\ \\ \\   / / /      /  \\ \\       /  \\ \\ / / /         _   \r
                        \\ \\ \\_/ / /      / /\\ \\ \\     / /\\ \\ \\\\ \\ \\__      /\\_\\ \r
                         \\ \\___/ /      / / /\\ \\ \\   / / /\\ \\_\\\\ \\___\\    / / / \r
                          \\ \\ \\_/      / / /  \\ \\_\\ / / /_/ / / \\__  /   / / /  \r
                           \\ \\ \\      / / /   / / // / /__\\/ /  / / /   / / /   \r
                            \\ \\ \\    / / /   / / // / /_____/  / / /   / / /    \r
                             \\ \\ \\  / / /___/ / // / /\\ \\ \\   / / /___/ / /     \r
                              \\ \\_\\/ / /____\\/ // / /  \\ \\ \\ / / /____\\/ /      \r
                               \\/_/\\/_________/ \\/_/    \\_\\/ \\/_________/       \r
                                                                                """;

        System.out.println("     Loading Yoru...");
        System.out.println(logo);

        showLine();        
        System.out.println("     Yo. Yoru here.");
        System.out.println("     Drop your command and I'll handle it from the shadows.");
        showLine();
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("     I'm out. Call me when you need clean execution.");
        showLine();
    }

    /**
     * Prints a line separator.
     */
    public void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays an error message.
     *
     * @param message Error details.
     */
    public void showError(String message) {
        System.out.println("     " + message);
    }

    /**
     * Displays all tasks in the current task list.
     *
     * @param tasks Task list to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("     Current targets on your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Displays feedback for a task marked as done.
     *
     * @param task Updated task.
     */
    public void showTaskMarked(Task task) {
        System.out.println("     Clean. Mission complete:");
        System.out.println("       " + task);
        showLine();
    }

    /**
     * Displays feedback for a task marked as not done.
     *
     * @param task Updated task.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("     Not finished. Putting this back in play:");
        System.out.println("       " + task);
        showLine();
    }

    /**
     * Displays feedback for a newly added task.
     *
     * @param task Added task.
     * @param taskCount Updated number of tasks.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("     Added. New objective locked:");
        System.out.println("       " + task);
        System.out.println("     You now have " + taskCount + " targets on the board.");
        showLine();
    }

    /**
     * Displays feedback for a deleted task.
     *
     * @param task Removed task.
     * @param taskCount Updated number of tasks.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("     Gone. Objective removed:");
        System.out.println("       " + task);
        System.out.println("     " + taskCount + " targets remain.");
        showLine();
    }

    /**
     * Displays tasks that match a keyword search.
     *
     * @param matchingTasks Matching tasks list.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println("     Here are the matching tasks in your list:");
        if (matchingTasks.isEmpty()) {
            System.out.println("     No matching tasks found.");
            showLine();
            return;
        }

        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + matchingTasks.get(i));
        }
        showLine();
    }
}