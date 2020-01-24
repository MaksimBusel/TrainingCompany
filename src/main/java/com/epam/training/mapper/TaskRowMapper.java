package com.epam.training.mapper;

import com.epam.training.entity.Course;
import com.epam.training.entity.Task;

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

    @Override
    public String getFieldsMapper() {
        StringBuilder builder = new StringBuilder();
        builder.append(Task.ID +", ");
        builder.append(Course.ID+", ");
        builder.append(Task.TASK_NAME+", ");
        builder.append(Task.DATE_FROM+", ");
        builder.append(Course.DATE_TO);
        return builder.toString();
    }
}
