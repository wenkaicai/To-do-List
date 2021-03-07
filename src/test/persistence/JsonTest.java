package persistence;

import model.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTask(String name, Date dueDate, String status, Task task) {
        assertEquals(name, task.getName());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(status, task.getStatus());
    }
}
