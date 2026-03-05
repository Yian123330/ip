import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file for persistent storage.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws YiyiException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws YiyiException {
        try {
            Path dataPath = Paths.get("./data");
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(taskToFileString(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new YiyiException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws YiyiException If there is an error reading the file or parsing its contents.
     */
    public ArrayList<Task> load() throws YiyiException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    Task task = fileStringToTask(line);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new YiyiException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Converts a Task object to a string format suitable for file storage.
     *
     * @param task The task to convert.
     * @return A string representation of the task for file storage.
     */
    private String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();

        if (task instanceof Todo) {
            sb.append("T | ");
            sb.append(task.isDone() ? "1" : "0").append(" | ");
            sb.append(task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append("D | ");
            sb.append(task.isDone() ? "1" : "0").append(" | ");
            sb.append(task.getDescription()).append(" | ");
            sb.append(deadline.by);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            sb.append("E | ");
            sb.append(task.isDone() ? "1" : "0").append(" | ");
            sb.append(task.getDescription()).append(" | ");
            sb.append(event.from).append(" | ").append(event.to);
        }
        return sb.toString();
    }

    /**
     * Converts a string from the storage file back into a Task object.
     *
     * @param line The line read from the storage file.
     * @return The Task object represented by the line.
     * @throws YiyiException If the line format is invalid or corrupted.
     */
    private Task fileStringToTask(String line) throws YiyiException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new YiyiException("Corrupted deadline data: " + line);
            }
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 5) {
                throw new YiyiException("Corrupted event data: " + line);
            }
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            throw new YiyiException("Unknown task type: " + type);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}