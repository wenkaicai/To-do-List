package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Date;

public class ToDoList implements Writable {
    private String name;
    private ArrayList<Task> tasksList;
    private static final int MAX_SIZE_TASKS = 5;

    public ToDoList(String name) {
        this.name = name;
        tasksList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    //EFFECTS: returns the task list size
    public int getSize() {
        return tasksList.size();
    }

    //REQUIRES: tasksList has at least one task
    //MODIFIES: nothing
    //EFFECTS: returns the given index task in tasksList
    public Task getTaskByIndex(int index) {
        return tasksList.get(index);
    }

    //EFFECTS: returns tasks
    public ArrayList<Task> getTasks() {
        return tasksList;
    }

    //MODIFIES: this
    //EFFECTS: add a new task to our ToDoList
    public ArrayList<Task> addTask(String name, Date dueDate, String status) {
        if (tasksList.size() >= MAX_SIZE_TASKS) {
            return tasksList;
        } else {
            tasksList.add(new Task(name, dueDate, status));
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: delete a task in our ToDoList
    public void deleteTask(String name) {
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).getName().equals(name)) {
                tasksList.remove(i);
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tasksList", tasksListToJson());
        return json;
    }

    // EFFECTS: returns tasks in this tasks list as a JSON array
    private JSONArray tasksListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasksList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}




