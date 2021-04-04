package ui;

import model.TooManyTasksException;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            new ToDoListApp();
        } catch (FileNotFoundException | ParseException | TooManyTasksException e) {
            System.out.println("Unable to run application: wrong command");
        }
    }
}