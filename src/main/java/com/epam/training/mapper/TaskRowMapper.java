package main.java.com.epam.training.mapper;

import main.java.com.epam.training.entity.Course;
import main.java.com.epam.training.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {


    @Override
    public Task map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Task.ID);
        String name = resultSet.getString(Task.TASK_NAME);
        String dateFrom = resultSet.getString(Task.DATE_FROM);
        String dateTo = resultSet.getString(Task.DATE_TO);
        Task task = new Task(id, name, dateFrom, dateTo);

        return task;
    }
}
