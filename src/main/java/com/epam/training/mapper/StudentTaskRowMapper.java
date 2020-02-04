package main.java.com.epam.training.mapper;

import main.java.com.epam.training.entity.StudentTask;
import main.java.com.epam.training.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentTaskRowMapper implements RowMapper<StudentTask>{

    @Override
    public StudentTask map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Task.ID);
        long studentTaskId = resultSet.getLong(StudentTask.STUDENT_TASK_ID);
        String name = resultSet.getString(Task.TASK_NAME);
        String dateFrom = resultSet.getString(Task.DATE_FROM);
        String dateTo = resultSet.getString(Task.DATE_TO);
        Integer mark = resultSet.getInt(StudentTask.MARK);
        String feedback = resultSet.getString(StudentTask.FEEDBACK);
        String filePath = resultSet.getString(StudentTask.FILE_PATH);
        StudentTask studentTask = new StudentTask(id, studentTaskId, name, dateFrom, dateTo, mark, feedback, filePath);

        return studentTask;
    }
}
