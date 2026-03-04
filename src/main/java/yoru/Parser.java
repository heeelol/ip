package yoru;

public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark ";
    private static final String COMMAND_UNMARK = "unmark ";
    private static final String COMMAND_TODO = "todo ";
    private static final String COMMAND_DEADLINE = "deadline ";
    private static final String COMMAND_EVENT = "event ";
    private static final String COMMAND_DELETE = "delete ";

    public static boolean parse(String command, TaskList tasks, Ui ui, Storage storage) throws YoruException {
        if (command.equalsIgnoreCase(COMMAND_BYE)) {
            ui.showGoodbye();
            return true;
        } else if (command.equalsIgnoreCase(COMMAND_LIST)) {
            ui.showTaskList(tasks);
            return false;
        } else if (command.startsWith(COMMAND_MARK)) {
            int index = parseTaskIndex(command, COMMAND_MARK.length());
            Task task = tasks.mark(index);
            ui.showTaskMarked(task);
            storage.save(tasks.getAll());
            return false;
        } else if (command.startsWith(COMMAND_UNMARK)) {
            int index = parseTaskIndex(command, COMMAND_UNMARK.length());
            Task task = tasks.unmark(index);
            ui.showTaskUnmarked(task);
            storage.save(tasks.getAll());
            return false;
        } else if (command.startsWith(COMMAND_TODO)) {
            String description = command.substring(COMMAND_TODO.length());
            if (description.isEmpty()) {
                throw new YoruException("The description of a todo cannot be empty.");
            }
            Task task = tasks.addTodo(description);
            ui.showTaskAdded(task, tasks.size());
            storage.save(tasks.getAll());
            return false;
        } else if (command.startsWith(COMMAND_DEADLINE)) {
            String args = command.substring(COMMAND_DEADLINE.length());
            if (!args.contains(" /by ")) {
                throw new YoruException("Deadline format: deadline <description> /by <yyyy-MM-dd>");
            }
            String[] parts = args.split(" /by ", 2);
            if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new YoruException("Deadline format: deadline <description> /by <yyyy-MM-dd>");
            }
            Task task = tasks.addDeadline(parts[0], parts[1]);
            ui.showTaskAdded(task, tasks.size());
            storage.save(tasks.getAll());
            return false;
        } else if (command.startsWith(COMMAND_EVENT)) {
            String args = command.substring(COMMAND_EVENT.length());
            if (!args.contains(" /from ") || !args.contains(" /to ")) {
                throw new YoruException("Event format: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
            }
            String[] parts = args.split(" /from ", 2);
            String[] timeParts = parts[1].split(" /to ", 2);
            if (parts[0].trim().isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new YoruException("Event format: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
            }
            Task task = tasks.addEvent(parts[0], timeParts[0], timeParts[1]);
            ui.showTaskAdded(task, tasks.size());
            storage.save(tasks.getAll());
            return false;
        } else if (command.startsWith(COMMAND_DELETE)) {
            int index = parseTaskIndex(command, COMMAND_DELETE.length());
            Task removedTask = tasks.delete(index);
            ui.showTaskDeleted(removedTask, tasks.size());
            storage.save(tasks.getAll());
            return false;
        }

        throw new YoruException("I don't understand that command.");
    }

    private static int parseTaskIndex(String command, int prefixLength) throws YoruException {
        try {
            return Integer.parseInt(command.substring(prefixLength).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new YoruException("Please provide a valid task number.");
        }
    }
}