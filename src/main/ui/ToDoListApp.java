package ui;

import model.Task;
import model.ToDoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

// task management application
public class ToDoListApp {
    private Task task;
    private ToDoList toDoList;
    private Scanner input;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // EFFECTS: runs the App
    public ToDoListApp() throws ParseException {
        runToDoList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runToDoList() throws ParseException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("s")) {
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ten -> editTaskName");
        System.out.println("\tes -> editTaskStatus");
        System.out.println("\ta -> addTask");
        System.out.println("\td -> deleteTask");
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
                if (toDoList.getTaskByIndex(i).getName() == name) {
                    System.out.println("Enter new name\n");
                    String newName = input.next();
                    toDoList.getTaskByIndex(i).setName(newName);
                }
                System.out.println("Task not exist\n");
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
                if (toDoList.getTaskByIndex(i).getName() == name) {
                    System.out.println("Enter new status\n");
                    String newStatus = input.next();
                    toDoList.getTaskByIndex(i).setName(newStatus);
                }
                System.out.println("Task not exist\n");
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
                if (toDoList.getTaskByIndex(i).getName() == name) {
                    toDoList.deleteTask(name);
                }
                System.out.println("Task not exist\n");
            }
        }
        for (Task task : toDoList.getTasks()) {
            System.out.println(task.getName());
            System.out.println(task.getDueDate());
            System.out.println(task.getStatus());
        }
    }
}





