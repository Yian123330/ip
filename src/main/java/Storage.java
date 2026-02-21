import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/yiyi.txt";

    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        Path dataPath = Paths.get("./data");
        if(!Files.exists(dataPath)){
            Files.createDirectories(dataPath);
        }

        FileWriter writer = new FileWriter(FILE_PATH);
        for (Task task : tasks){
            writer.write(taskToFileString(task) + "\n");
        }
        writer.close();
    }

    public static ArrayList<Task> loadTasks() throws IOException, YiyiException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if(!file.exists()){
            return tasks;
        }
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()){
                Task task = fileStringToTask(line);
                tasks.add(task);
            }
        }
        scanner.close();
        return tasks;
    }

    private static String taskToFileString(Task task){
        StringBuilder sb = new StringBuilder();

        if(task instanceof Todo){
            sb.append("T | ");
            sb.append(task.isDone() ? "1" : "0" ).append(" | ");
            sb.append(task.getDescription());
        }else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append("D | ");
            sb.append(task.isDone() ? "1" : "0").append(" | ");
            sb.append(task.getDescription()).append(" | ");
            sb.append(deadline.by);
        }else if(task instanceof Event){
            Event event = (Event) task;
            sb.append("E | ");
            sb.append(task.isDone() ? "1" : "0").append(" | ");
            sb.append(task.getDescription()).append(" | ");
            sb.append(event.from).append(" | ").append(event.to);
        }
        return sb.toString();
    }

    private static Task fileStringToTask(String line) throws YiyiException{
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch(type){
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4){
                throw new YiyiException("Corrupted deadline data: " + line);
            }
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if(parts.length < 5){
                throw new YiyiException("Corrupted event data: " + line);
            }
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            throw new YiyiException("Unknown task type: " + type);
        }
        if(isDone){
            task.markAsDone();
        }
        return task;
    }
}
