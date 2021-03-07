package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList td = new ToDoList("todolist1");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDoList() {
        try {
            ToDoList td = new ToDoList("todolist1");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            td = reader.read();
            assertEquals("todolist1", td.getName());
            assertEquals(0, td.getSize());
        } catch (IOException | ParseException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDoList() throws ParseException {
        try {
            ToDoList td = new ToDoList("todolist1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            td.addTask("homework1", sdf.parse("2021-02-11"), "Doing");
            td.addTask("homework2", sdf.parse("2021-02-12"), "Haven't start");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoList.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
            td = reader.read();
            assertEquals("todolist1", td.getName());
            ArrayList<Task> tasks = td.getTasks();
            assertEquals(2, tasks.size());
            checkTask("homework1", sdf.parse("2021-02-11"), "Doing", tasks.get(0));
            checkTask("homework2", sdf.parse("2021-02-12"), "Haven't start", tasks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
