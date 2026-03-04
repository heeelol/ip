package yoru;

import java.util.Scanner;

public class Ui {
    private static final String LINE_SEPARATOR = "_______________________________________";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

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

    public void showGoodbye() {
        System.out.println("     I'm out. Call me when you need clean execution.");
        showLine();
    }

    public void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public void showError(String message) {
        System.out.println("     " + message);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("     Current targets on your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    public void showTaskMarked(Task task) {
        System.out.println("     Clean. Mission complete:");
        System.out.println("       " + task);
        showLine();
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("     Not finished. Putting this back in play:");
        System.out.println("       " + task);
        showLine();
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("     Added. New objective locked:");
        System.out.println("       " + task);
        System.out.println("     You now have " + taskCount + " targets on the board.");
        showLine();
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("     Gone. Objective removed:");
        System.out.println("       " + task);
        System.out.println("     " + taskCount + " targets remain.");
        showLine();
    }
}