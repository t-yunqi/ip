package chatowo.task;

/**
 * Abstract base class for all task types.
 * Provides common functionality for tasks in the system.
 */
public abstract class Task {
    public String name;
    public boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Updates the isDone status to true.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Updates the isDone status to false.
     */
    public void setNotDone() {
        this.done = false;
    }

    /**
     * Converts the task to a string format suitable for storage.
     *
     * @return String representation for data storage
     */
    public String toDataString() {
        if (this.done) {
            return " | 1 | " + name;
        } else {
            return " | 0 | " + name;
        }
    }

    /**
     * Creates a string representation of the task.
     *
     * @return Formatted string showing task status and details
     */
    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
