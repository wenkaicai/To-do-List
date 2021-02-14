package model;

import java.util.ArrayList;
import java.util.Date;

public class ToDoList {
    private ArrayList<Task> tasksList;
    private static final int MAX_SIZE_TASKS = 5;

    public ToDoList() {
        tasksList = new ArrayList<>();
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
}




