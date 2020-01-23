package com.epam.training.dao.impl;

import com.epam.training.dao.AbstractDao;
import com.epam.training.dao.StudentTaskDao;
import com.epam.training.entity.StudentTask;
import com.epam.training.exception.DaoException;
import com.epam.training.mapper.StudentTaskRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentTaskDaoImpl extends AbstractDao<StudentTask> implements StudentTaskDao {
    private static final String GET_MY_MARKS_BY_COURSE_ID = "SELECT tasks.*, mark, feedback FROM student_tasks  INNER JOIN tasks " +
            "ON tasks.course_id=student_tasks.course_id WHERE user_id=? AND student_tasks.course_id=?";

    //not use
    private static final String FIND_BY_ID ="SELECT * FROM student_tasks WHERE task_id = ?";
    private static final String REMOVE_BY_ID ="DELETE FROM student_tasks WHERE task_id = ?";
    private static final String SAVE_STUDENT_TASK = "INSERT INTO student_tasks (task_name, mark, feedback) VALUES(?, ?, ?)";


    public StudentTaskDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return StudentTask.TABLE;
    }

    @Override
    public Optional<StudentTask> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new StudentTaskRowMapper(), id);
    }

    @Override
    public void save(StudentTask studentTask) throws DaoException {
        String taskName = studentTask.getName();
        int mark = studentTask.getMark();
        String feedback = studentTask.getFeedback();
        try(PreparedStatement statement = createStatement(SAVE_STUDENT_TASK, taskName,mark, feedback)){
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeById(Long id) throws DaoException {
        try(PreparedStatement statement = createStatement(REMOVE_BY_ID, id)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<StudentTask> getMyMarks(long userId, String courseId) throws DaoException {
        return executeQuery(GET_MY_MARKS_BY_COURSE_ID,new StudentTaskRowMapper(), userId, courseId);
    };
}
