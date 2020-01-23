package com.epam.training.entity;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Identifable, Serializable {
    public static final String ID = "task_id";
    public static final String TASK_NAME = "task_name";
    public static final String DATE_FROM = "date_from";
    public static final String DATE_TO = "date_to";
    public static final String TABLE = "tasks";

    private long id;
    private String name;
    private String dateFrom;
    private String dateTo;

    public Task(long id, String name, String dateFrom, String dateTo) {
        this.id = id;
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id &&
                name != null ?name.equals(task.name) : task.name == null &&
                dateFrom != null ? dateFrom.equals(task.dateFrom) : task.dateFrom == null &&
                dateTo != null ? dateTo.equals(task.dateTo) : task.dateTo == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return "Task{" +"id=" + id + ", name='" + name +", dateFrom='" + dateFrom + ", dateTo='" + dateTo +'}';
    }
}
