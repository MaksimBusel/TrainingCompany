package com.epam.training.mapper;

import com.epam.training.entity.StudentTask;
import com.epam.training.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentTaskRowMapper implements RowMapper<StudentTask>{

    @Override
    public StudentTask map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Task.ID);
        String name = resultSet.getString(Task.TASK_NAME);
        String dateFrom = resultSet.getString(Task.DATE_FROM);
        String dateTo = resultSet.getString(Task.DATE_TO);
        Integer mark = resultSet.getInt(StudentTask.MARK);
        String feedback = resultSet.getString(StudentTask.FEEDBACK);
        StudentTask studentTask = new StudentTask(id,name, dateFrom, dateTo, mark, feedback);

        return studentTask;
    }
}
