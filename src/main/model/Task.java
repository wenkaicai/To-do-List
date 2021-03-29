package model;

import org.json.JSONObject;
import persistence.Writable;

import java.text.SimpleDateFormat;
import java.util.Date;

// Represents a Task having a name, due date and status
public class Task implements Writable {
    private String name;                   // the Task name
    private Date dueDate;                  // the Task due date
    private String status;                 // the current status of the Task

    //REQUIRES: taskName has a non-zero length
    //EFFECTS: Constructs task with given description
    public Task(String name, Date dueDate, String status) {
        this.name = name;
        this.dueDate = dueDate;
        this.status = status;
    }

    // EFFECTS: return this task's name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: set the name for this task
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: return this task's dueDate
    public Date getDueDate() {
        return dueDate;
    }

    // MODIFIES: this
    // EFFECTS: set the due day for this task
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // EFFECTS: return this task's status
    public String getStatus() {
        return status;
    }

    // MODIFIES: this
    // EFFECTS: set the status for this task
    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        json.put("dueDate", sdf.format(dueDate));
        json.put("status", status);
        return json;
    }
}
