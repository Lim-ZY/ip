package mark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles reading and writing of storage file from disk.
 */
public class Storage {
    /**
     * Input format of date and time
     **/
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Data file object
     **/
    private final File file;
    /**
     * Extracted TaskList
     **/
    private List<Task> tasks;

    /**
     * Returns Storage object.
     * Pass in path to data file if exists.
     *
     * @param filePath String.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Saves TaskList into data file.
     *
     * @param tasks List of Tasks.
     */
    public void save(List<Task> tasks) {
        this.tasks = tasks;
        try {
            FileWriter fileWriter = new FileWriter(this.file.getPath());
            StringBuilder sb = new StringBuilder();
            for (Task task : tasks) {
                sb.append(task.toSaveString());
                sb.append("\n");
            }
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
            Ui.println("File not found");
        }
    }

    /**
     * Restores TaskList from previous session.
     * Returns list of tasks previously saved into data file.
     * Creates parent directory and file if data file does not exist.
     *
     * @return List of Tasks.
     * @throws IOException if file cannot be created.
     */
    public List<Task> getFileContents() throws IOException {
        this.tasks = new ArrayList<>();
        File parentDir = this.file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!this.file.exists()) {
            this.file.createNewFile();
        }

        Scanner sc = new Scanner(this.file);
        while (sc.hasNext()) {
            String[] entry = sc.nextLine().split(" \\| ");
            boolean isDone = entry[1].equals("1") ? true : false;
            switch (entry[0]) {
            case "T":
                this.tasks.add(new Todo(entry[2], isDone));
                break;
            case "D":
                this.tasks.add(new Deadline(entry[2], isDone, LocalDateTime.parse(entry[3], Storage.FORMAT)));
                break;
            case "E":
                String[] fromTo = entry[3].split(" \\|\\| ");
                this.tasks.add(new Event(entry[2], isDone,
                        LocalDateTime.parse(fromTo[0], Storage.FORMAT),
                        LocalDateTime.parse(fromTo[1], Storage.FORMAT)));
                break;
            default:
                throw new IOException("Unknown entry: " + entry[0]);
            }
        }

        return new ArrayList<>(this.tasks);
    }
}
