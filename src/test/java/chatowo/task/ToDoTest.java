package chatowo.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDataString() {
        Task t = new ToDo("2103 ip");
        t.setDone();
        assertEquals("T | 1 | 2103 ip", t.toString());
    }
}
