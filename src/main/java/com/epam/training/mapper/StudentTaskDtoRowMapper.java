package com.epam.training.mapper;

import com.epam.training.dto.StudentTaskDto;
import com.epam.training.entity.StudentTask;
import com.epam.training.entity.Task;
import com.epam.training.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentTaskDtoRowMapper implements RowMapper<StudentTaskDto> {

    @Override
    public StudentTaskDto map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(StudentTaskDto.ID);
        long userId = resultSet.getLong(User.ID);
        long taskId = resultSet.getLong(Task.ID);
        String studentFirstName = resultSet.getString(User.FIRST_NAME);
        String studentLastName = resultSet.getString(User.LAST_NAME);
        String taskName = resultSet.getString(Task.TASK_NAME);
        Integer mark = resultSet.getInt(StudentTask.MARK);
        String feedback = resultSet.getString(StudentTask.FEEDBACK);
        StudentTaskDto studentTask = new StudentTaskDto(id, userId, taskId, studentFirstName, studentLastName, taskName, mark, feedback);

        return studentTask;
    }
}
