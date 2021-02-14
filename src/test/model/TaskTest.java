package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        task = new Task("test1", sdf.parse("2021-02-10"), "Doing");
    }

    @Test
    public void testGetName() {
        assertEquals("test1", task.getName());
    }

    @Test
    public void testSetName() {
        task.setName("CS210");
        assertEquals("CS210", task.getName());
    }

    @Test
    public void testGetDueDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(sdf.parse("2021-02-10"), task.getDueDate());
    }

    @Test
    public void testSetDueDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        task.setDueDate(sdf.parse("2021-02-12"));
        assertEquals(sdf.parse("2021-02-12"), task.getDueDate());
    }

    @Test
    public void testGetStatus() {
        assertEquals("Doing", task.getStatus());
    }

    @Test
    public void testSetStatus() {
        task.setStatus("Finished");
        assertEquals("Finished", task.getStatus());
    }
}