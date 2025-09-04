package chatowo;

import chatowo.task.Deadline;
import chatowo.task.Event;
import chatowo.task.Task;
import chatowo.task.TaskList;
import chatowo.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public TaskList readTaskList() throws IOException {
        TaskList tasks = new TaskList();

        File directory = new File("./data");
        File file = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        while (line != null) {
            String[] taskDetails = line.split(" \\| ");
            if (taskDetails.length < 3) {
                throw new IOException("invalid data format");
            }
            switch (taskDetails[0]) {
            case "T":
                ToDo t = new ToDo(taskDetails[2]);
                tasks.add(t);
                if (taskDetails[1].equals("1")) {
                    t.setDone();
                }
                break;

            case "D":
                if (taskDetails.length < 4) {
                    throw new IOException("invalid data format");
                }
                Deadline d = new Deadline(taskDetails[2], taskDetails[3]);
                tasks.add(d);
                if (taskDetails[1].equals("1")) {
                    d.setDone();
                }
                break;

            case "E":
                if (taskDetails.length < 5) {
                    throw new IOException("invalid data format");
                }
                Event e = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                tasks.add(e);
                if (taskDetails[1].equals("1")) {
                    e.setDone();
                }
                break;
            }
            line = br.readLine();
        }
        br.close();
        return tasks;
    }

    private interface LineEditor {
        /**
         * @param line      The current line from the file.
         * @param lineIndex The index of the current line.
         * @return The line to write, or null to skip (remove) the line.
         */
        String edit(String line, int lineIndex);
    }

    private void processFile(LineEditor editor) throws IOException {
        File originalFile = new File(path);
        File tempFile = new File(path + ".tmp");

        tempFile.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        int lineCount = -1;

        currentLine = br.readLine();
        while (currentLine != null) {
            lineCount++;
            String editedLine = editor.edit(currentLine, lineCount);
            if (editedLine != null) {
                bw.write(editedLine);
                bw.newLine();
            }
            currentLine = br.readLine();
        }

        br.close();
        bw.close();

        if (!originalFile.delete()) {
            throw new IOException("Could not delete original file");
        }
        if (!tempFile.renameTo(originalFile)) {
            throw new IOException("Could not rename temporary file");
        }
    }

    public void removeFromTaskList(int index) throws IOException {
        processFile((line, lineIndex) -> lineIndex == index ? null : line);
    }

    public void editTaskList(int index, Task task) throws IOException {
        processFile((line, lineIndex) -> lineIndex == index ? task.toDataString() : line);
    }

    public void addTask(Task task) throws IOException{
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(path, true));
        bw.write(task.toDataString());
        bw.newLine();
        bw.close();
    }
}
