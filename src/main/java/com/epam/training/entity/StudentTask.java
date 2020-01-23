package com.epam.training.entity;

import java.io.Serializable;
import java.util.Objects;

public class StudentTask implements Identifable, Serializable {
    public static final String MARK = "mark";
    public static final String FEEDBACK = "feedback";
    public static final String TABLE = "student_tasks";

    private Task task;
    private Integer mark;
    private String feedback;

    public StudentTask(long id, String name, String dateFrom, String dateTo, Integer mark, String feedback) {
        task = new Task(id, name, dateFrom, dateTo);
        this.mark = mark;
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public long getId() {
        return task.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        StudentTask that = (StudentTask) o;
        return task !=null? task.equals(that.task): that.task == null &&
               mark != null ? mark.equals(that.mark) : that.mark == null &&
               feedback != null ? feedback.equals(that.feedback) : that.feedback == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, mark, feedback);
    }

    @Override
    public String toString() {
        return task.toString() + "mark=" + mark + ", feedback='" + feedback  + '}';
    }
}
