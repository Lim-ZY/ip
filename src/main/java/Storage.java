import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private File file;
    private List<Task> tasks;
    
    public Storage(String filePath) {
        this.file = new File(filePath);
    }
    
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
            System.out.println("File not found");
        }
    }
    
    public List<Task> getFileContents() {
        this.tasks = new ArrayList<>();
        try {
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
                    this.tasks.add(new Deadline(entry[2], isDone, LocalDate.parse(entry[3])));
                    break;
                case "E":
                    String[] fromTo = entry[3].split(" \\|\\| ");
                    this.tasks.add(new Event(entry[2], isDone, LocalDate.parse(fromTo[0]), LocalDate.parse(fromTo[1])));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Could not create file");
        }
        
        return new ArrayList<>(this.tasks);
    }
}
