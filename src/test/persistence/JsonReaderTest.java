package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList td = reader.read();
            fail("IOException expected");
        } catch (IOException | ParseException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList td = reader.read();
            assertEquals("todolist1", td.getName());
            assertEquals(0, td.getSize());
        } catch (IOException | ParseException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.json");
        try {
            ToDoList td = reader.read();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            assertEquals("todolist1", td.getName());
            ArrayList<Task> tasks = td.getTasks();
            assertEquals(2, td.getSize());
            checkTask("homework1", sdf.parse("2021-02-11"), "Doing", tasks.get(0));
            checkTask("homework2", sdf.parse("2021-02-12"), "Haven't start", tasks.get(1));
        } catch (IOException | ParseException e) {
            fail("Couldn't read from file");
        }
    }
}
