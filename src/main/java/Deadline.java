import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public LocalDate deadline;

    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toDataString() {
        return "D" + super.toDataString() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
