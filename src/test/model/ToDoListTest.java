package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
    private ToDoList toDoList;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        toDoList = new ToDoList("todolist1");
    }

    @Test
    void getSizeTest() throws ParseException {
        assertEquals(0, toDoList.getSize());
        toDoList.addTask("homework1", sdf.parse("2021-02-11"), "Doing");
        assertEquals(1, toDoList.getSize());
        toDoList.addTask("homework2", sdf.parse("2021-02-12"), "Haven't start");
        assertEquals(2, toDoList.getSize());
    }

    @Test
    void addTaskTest() throws ParseException {
        assertEquals(0, toDoList.getSize());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        toDoList.addTask("homework1", sdf.parse("2021-02-11"), "Doing");
        assertEquals("homework1", toDoList.getTaskByIndex(0).getName());
        assertEquals(sdf.parse("2021-02-11"), toDoList.getTaskByIndex(0).getDueDate());
        assertEquals("Doing", toDoList.getTaskByIndex(0).getStatus());
    }

    @Test
    void addTaskFailedTest() throws ParseException {
        assertEquals(0, toDoList.getSize());
        toDoList.addTask("homework1", sdf.parse("2021-02-11"), "Doing");
        toDoList.addTask("homework2", sdf.parse("2021-02-12"), "Haven't start");
        toDoList.addTask("homework3", sdf.parse("2021-02-13"), "Haven't start");
        toDoList.addTask("homework4", sdf.parse("2021-02-14"), "Haven't start");
        toDoList.addTask("homework5", sdf.parse("2021-02-15"), "Haven't start");
        assertEquals(5, toDoList.getSize());
        toDoList.addTask("homework6", sdf.parse("2021-02-16"), "Haven't start");
        assertEquals(5, toDoList.getSize());
        assertEquals("homework1", toDoList.getTaskByIndex(0).getName());
        assertEquals("homework2", toDoList.getTaskByIndex(1).getName());
        assertEquals("homework3", toDoList.getTaskByIndex(2).getName());
        assertEquals("homework4", toDoList.getTaskByIndex(3).getName());
        assertEquals("homework5", toDoList.getTaskByIndex(4).getName());
    }

    @Test
    void getTaskByIndexTest() throws ParseException {
        assertEquals(0, toDoList.getSize());
        toDoList.addTask("homework1", sdf.parse("2021-02-11"), "Doing");
        toDoList.addTask("homework2", sdf.parse("2021-02-12"), "Haven't start");
        assertEquals("homework1", toDoList.getTaskByIndex(0).getName());
        assertEquals(sdf.parse("2021-02-11"), toDoList.getTaskByIndex(0).getDueDate());
        assertEquals("Doing", toDoList.getTaskByIndex(0).getStatus());
        assertEquals("homework2", toDoList.getTaskByIndex(1).getName());
        assertEquals(sdf.parse("2021-02-12"), toDoList.getTaskByIndex(1).getDueDate());
        assertEquals("Haven't start", toDoList.getTaskByIndex(1).getStatus());
    }

    @Test
    void getTasksTest() throws ParseException {
        assertEquals(0, toDoList.getSize());
        toDoList.addTask("homework1", sdf.parse("2021-02-11"), "Doing");
        toDoList.addTask("homework2", sdf.parse("2021-02-12"), "Haven't start");
        ArrayList<Task> arrayList = new ArrayList<>();
        arrayList.add(new Task("homework1", sdf.parse("2021-02-11"), "Doing"));
        arrayList.add(new Task("homework2", sdf.parse("2021-02-12"), "Haven't start"));
        assertEquals(arrayList.get(0).getName(), toDoList.getTasks().get(0).getName());
        assertEquals(arrayList.get(0).getDueDate(), toDoList.getTasks().get(0).getDueDate());
        assertEquals(arrayList.get(0).getStatus(), toDoList.getTasks().get(0).getStatus());
        assertEquals(arrayList.get(1).getName(), toDoList.getTasks().get(1).getName());
        assertEquals(arrayList.get(1).getDueDate(), toDoList.getTasks().get(1).getDueDate());
        assertEquals(arrayList.get(1).getStatus(), toDoList.getTasks().get(1).getStatus());
    }

    @Test
    void deleteTaskTest() throws ParseException {
        assertEquals(0, toDoList.getSize());
        toDoList.addTask("homework1", sdf.parse("2021-02-11"), "Doing");
        toDoList.addTask("homework2", sdf.parse("2021-02-12"), "Haven't start");
        assertEquals(2, toDoList.getSize());
        toDoList.deleteTask("homework1");
        toDoList.deleteTask("homework3");
        assertEquals(1, toDoList.getSize());
        assertEquals("homework2", toDoList.getTaskByIndex(0).getName());
        assertEquals(sdf.parse("2021-02-12"), toDoList.getTaskByIndex(0).getDueDate());
        assertEquals("Haven't start", toDoList.getTaskByIndex(0).getStatus());
    }
}
