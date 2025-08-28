package chatowo.task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public void done(int index) {
        this.get(index).setDone();
    }

    public void undone(int index) {
        this.get(index).setNotDone();
    }

    @Override
    public boolean add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("Task is null");
        } else {
            return super.add(task);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            String index = String.valueOf(i + 1);
            str += ("\n    " + index + "." + this.get(i).toString());
        }
        return str;
    }
}
