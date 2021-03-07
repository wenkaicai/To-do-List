package persistence;

import model.ToDoList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads to-do-list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException, ParseException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses to-do-list from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) throws ParseException {
        String name = jsonObject.getString("name");
        ToDoList td = new ToDoList(name);
        addTasks(td, jsonObject);
        return td;
    }

    // MODIFIES: td
    // EFFECTS: parses tasks from JSON object and adds them to to-do-list
    private void addTasks(ToDoList td, JSONObject jsonObject) throws ParseException {
        JSONArray jsonArray = jsonObject.getJSONArray("tasksList");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(td, nextTask);
        }
    }

    // MODIFIES: td
    // EFFECTS: parses task from JSON object and adds it to to-do-list
    private void addTask(ToDoList td, JSONObject jsonObject) throws ParseException {
        String name = jsonObject.getString("name");
        String dueDate = jsonObject.getString("dueDate");
        String status = jsonObject.getString("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dueDate);
        td.addTask(name, date, status);
    }
}
