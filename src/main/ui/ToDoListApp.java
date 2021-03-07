package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

// task management application
public class ToDoListApp {
    private static final String JSON_STORE = "./data/todolist.json";
    private ToDoList toDoList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    SimpleDateFormat sdf;

    {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    // EFFECTS: runs the App
    public ToDoListApp() throws ParseException, FileNotFoundException {
        input = new Scanner(System.in);
        toDoList = new ToDoList("todolist1");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runToDoList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runToDoList() throws ParseException {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nHave a great Day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws ParseException {
        if (command.equals("en")) {
            doEditTaskName();
        } else if (command.equals("es")) {
            doEditTaskStatus();
        } else if (command.equals("a")) {
            doAddTask();
        } else if (command.equals("d")) {
            doDeleteTask();
        } else if (command.equals("v")) {
            doViewTasks();
        } else if (command.equals("s")) {
            doSaveToDoList();
        } else if (command.equals("l")) {
            doLoadToDoList();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ten -> editTaskName");
        System.out.println("\tes -> editTaskStatus");
        System.out.println("\ta -> addTask");
        System.out.println("\td -> deleteTask");
        System.out.println("\tv -> viewTasks");
        System.out.println("\ts -> save to-do-list to file");
        System.out.println("\tl -> load to-do-list from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: Edit the task name
    private void doEditTaskName() {
        if (toDoList.getSize() == 0) {
            System.out.println("No task to edit\n");
        } else {
            System.out.println("Enter the task name you want to edit\n");
            String name = input.next();
            for (int i = 0; i < toDoList.getSize(); i++) {
                if (toDoList.getTaskByIndex(i).getName().equals(name)) {
                    System.out.println("Enter new name\n");
                    String newName = input.next();
                    toDoList.getTaskByIndex(i).setName(newName);
                    System.out.println("Task name changed!\n");
                }
            }
        }
        for (Task task : toDoList.getTasks()) {
            System.out.println(task.getName());
            System.out.println(task.getDueDate());
            System.out.println(task.getStatus());
        }
    }

    // MODIFIES: this
    // EFFECTS: Edit the task status
    private void doEditTaskStatus() {
        if (toDoList.getSize() == 0) {
            System.out.println("No task to edit\n");
        } else {
            System.out.println("Enter the task name you want to edit\n");
            String name = input.next();
            for (int i = 0; i < toDoList.getSize(); i++) {
                if (toDoList.getTaskByIndex(i).getName().equals(name)) {
                    System.out.println("Enter new status\n");
                    String newStatus = input.next();
                    toDoList.getTaskByIndex(i).setStatus(newStatus);
                    System.out.println("Task status changed!\n");
                }
            }
        }
        for (Task task : toDoList.getTasks()) {
            System.out.println(task.getName());
            System.out.println(task.getDueDate());
            System.out.println(task.getStatus());
        }
    }

    // MODIFIES: this
    // EFFECTS: add a task to ToDoList
    private void doAddTask() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Enter new Task name\n");
        String name = input.next();
        System.out.println("Enter new Task dueDate\n");
        String dueDate = input.next();
        System.out.println("Enter new Task status\n");
        String status = input.next();
        toDoList.addTask(name, sdf.parse(dueDate), status);
        for (Task task : toDoList.getTasks()) {
            System.out.println(task.getName());
            System.out.println(task.getDueDate());
            System.out.println(task.getStatus());
        }
    }


    // MODIFIES: this
    // EFFECTS: delete the task by name
    private void doDeleteTask() {
        if (toDoList.getSize() == 0) {
            System.out.println("No task to delete\n");
        } else {
            System.out.println("Enter the task name you want to delete\n");
            String name = input.next();
            for (int i = 0; i < toDoList.getSize(); i++) {
                if (toDoList.getTaskByIndex(i).getName().equals(name)) {
                    toDoList.deleteTask(name);
                    System.out.println("Task deleted!\n");
                }
            }
        }
        for (Task task : toDoList.getTasks()) {
            System.out.println(task.getName());
            System.out.println(task.getDueDate());
            System.out.println(task.getStatus());
        }
    }

    // MODIFIES: nothing
    // EFFECTS: view user tasks in To-Do List
    private void doViewTasks() {
        if (toDoList.getSize() == 0) {
            System.out.println("No task to view\n");
        } else {
            for (Task task : toDoList.getTasks()) {
                System.out.println(task.getName());
                System.out.println(task.getDueDate());
                System.out.println(task.getStatus());
            }
        }
    }

    // EFFECTS: saves the to-do-list to file
    private void doSaveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();
            System.out.println("Saved " + toDoList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads to-do-list from file
    private void doLoadToDoList() {
        try {
            toDoList = jsonReader.read();
            System.out.println("Loaded " + toDoList.getName() + " from " + JSON_STORE);
        } catch (IOException | ParseException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}





