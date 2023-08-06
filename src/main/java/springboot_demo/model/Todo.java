package springboot_demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Todo {
    private int id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private int priority;
    private boolean done;

    public Todo(int id, String title, String description, LocalDateTime deadline, int priority,
                boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        if (priority >= 0 && priority <= 10) {
            this.priority = priority;
        } else {
            System.out.println("Invalid value : Priority must be between 0 and 10.");
        }
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\n" +
                "Title: " + this.getTitle() + "\n" +
                "Description: " + this.getDescription() + "\n" +
                "Deadline: " + this.getDeadline() + "\n" +
                "Priority: " + this.getPriority() + "\n" +
                "--------------------";
    }
}
