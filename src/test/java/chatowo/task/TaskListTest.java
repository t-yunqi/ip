package chatowo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTask_validTasks_success() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("read book");
        Task event = new Deadline("meeting", "2024-01-01");

        taskList.add(todo);
        taskList.add(event);

        assertEquals(2, taskList.size());
        assertEquals(todo, taskList.get(0));
        assertEquals(event, taskList.get(1));
    }

    @Test
    public void addTask_nullTask_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaskList().add(null);
        });
    }
}
