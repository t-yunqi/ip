public class Task {
    public String name;
    public boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public void setDone() {
        this.done = true;
    }

    public void setNotDone() {
        this.done = false;
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
