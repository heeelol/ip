package yoru;

/**
 * Main class for the Yoru chatbot application.
 * Manages task list operations and user interactions.
 */
public class Yoru {
    private static final String FILE_PATH = "./data/yoru.txt";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Yoru(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String command = ui.readCommand();
                ui.showLine();

                boolean isExit = Parser.parse(command, tasks, ui, storage);
                if (isExit) {
                    return;
                }
            } catch (YoruException e) {
                ui.showError(e.getMessage());
                ui.showLine();
            }
        }
    }

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Yoru(FILE_PATH).run();
    }
}