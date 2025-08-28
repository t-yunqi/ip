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
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            String index = String.valueOf(i + 1);
            str += ("\n    " + index + "." + this.get(i).toString());
        }
        return str;
    }
}
