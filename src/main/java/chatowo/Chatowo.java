package chatowo;

import chatowo.task.Task;
import chatowo.task.TaskList;

import java.io.IOException;

public class Chatowo {
    // initialise task list
    private TaskList list = new TaskList();
    private final Storage storage = new Storage("./data/chatowo.txt");
    private final Ui ui = new Ui();
    private final Parser parser = new Parser(this);

    public void reply(String msg) {
        ui.reply(msg);
    }

    public void listTasks() {
        ui.listTasks(list);
    }

    public void dateTimeError() {
        ui.dateTimeError();
    }

    public void addTask(Task task) {
        list.add(task);
        ui.addTask(task, list.size());

        try {
            storage.addTask(task);
        } catch (Exception e) {
            ui.reply(e.getMessage());
        }
    }

    public void readTaskList() {
        try {
            list = storage.readTaskList();
        } catch (IOException e) {
            ui.reply(e.getMessage());
        }
    }

    public void removeFromTaskList(int index) {
        try {
            storage.removeFromTaskList(index);
        } catch (IOException e) {
            ui.reply(e.getMessage());
        }
    }

    public void editTaskList(int index, Task task) {
        try {
            storage.editTaskList(index, task);
        } catch (Exception e) {
            ui.reply(e.getMessage());
        }
    }

    public void mark(int index) {
        Task t = list.get(index);
        list.done(index);
        this.editTaskList(index, t);
        ui.doneTask(t);
    }

    public void unmark(int index) {
        Task t = list.get(index);
        list.undone(index);
        this.editTaskList(index, t);
        ui.undoneTask(t);
    }

    public void delete(int index) {
        Task t = list.remove(index);
        list.trimToSize();
        this.removeFromTaskList(index);
        ui.deleteTask(t);
    }

    public void find(String phrase) {
        TaskList matchingTasks = new TaskList();
        list.forEach((task) -> {
            if (task.getName().contains(phrase)) {
                matchingTasks.add(task);
            }
        });
        ui.listMatchingTasks(matchingTasks);
    }

    public void run() {
        ui.greet();

        // read data file
        this.readTaskList();

        // continuously check input
        parser.parse();
        ui.bye();
    }

    public static void main(String[] args) {
        new Chatowo().run();
    }
}
