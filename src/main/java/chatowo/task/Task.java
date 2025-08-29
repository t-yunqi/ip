package chatowo.task;

public class Task {
    public String name;
    public boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDone() {
        this.done = true;
    }

    public void setNotDone() {
        this.done = false;
    }

    public String toDataString() {
        if (this.done) {
            return " | 1 | " + name;
        } else {
            return " | 0 | " + name;
        }
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
