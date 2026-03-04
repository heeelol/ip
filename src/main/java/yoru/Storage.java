package yoru;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to disk.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Saves the current task list to disk.
    public void save(ArrayList<Task> tasks) {
    try {
        Path path = Paths.get(filePath);
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
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
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;  // Return an empty list if the file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(parseTask(line));
                } catch (Exception e) {
                    System.out.println("     Warning: Skipping corrupted entry: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("     Warning: Could not load tasks: " + e.getMessage());
        }
        return tasks;
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