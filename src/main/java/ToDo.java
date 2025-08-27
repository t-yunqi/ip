public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toDataString() {
        return "T" + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
