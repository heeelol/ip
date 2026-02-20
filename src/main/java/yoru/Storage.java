package yoru;

import java.io.*;
import java.nio.file.*;

/**
 * Handles loading and saving tasks to disk.
 */
public class Storage {
    private static final String FILE_PATH = "./data/yoru.txt";

    // Saves the current task list to disk.
    public static void save(Task[] tasks, int taskCount) {
        try {
            Files.createDirectories(Paths.get("./data"));
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
                for (int i = 0; i < taskCount; i++) {
                    writer.println(tasks[i].toFileFormat());
                }
            }
        } catch (IOException e) {
            System.out.println("     Warning: Could not save tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from disk into the provided array.
     * Returns the number of tasks loaded.
     */
    public static int load(Task[] tasks) {
        int taskCount = 0;
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return taskCount;  // first run, no file yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks[taskCount] = parseTask(line);
                    taskCount++;
                } catch (Exception e) {
                    System.out.println("     Warning: Skipping corrupted entry: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("     Warning: Could not load tasks: " + e.getMessage());
        }
        return taskCount;
    }

    private static Task parseTask(String line) throws Exception {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Task task;
        
        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new Exception("Unknown task type: " + parts[0]);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}