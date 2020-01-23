package com.epam.training.dao.impl;

import com.epam.training.dao.AbstractDao;
import com.epam.training.dao.TaskDao;
import com.epam.training.entity.Task;
import com.epam.training.exception.DaoException;
import com.epam.training.mapper.TaskRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {
    private static final String GET_TASKS_BY_COURSE_ID ="SELECT * FROM tasks WHERE course_id=?";
    //not use
    private static final String FIND_BY_ID ="SELECT * FROM tasks WHERE task_id = ?";
    private static final String REMOVE_BY_ID ="DELETE FROM tasks WHERE task_id = ?";
    private static final String SAVE_TASK = "INSERT INTO tasks (task_name, date_from, date_to, course_id) VALUES(?, ?, ?, ?)";

    public TaskDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return Task.TABLE;
    }

    @Override
    public Optional<Task> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new TaskRowMapper(), id);
    }

    @Override
    public void save(Task task) throws DaoException {
        String taskName = task.getName();
        String dateFrom = task.getDateFrom();
        String dateTo = task.getDateTo();
        //String courseId = ;
        try(PreparedStatement statement = createStatement(SAVE_TASK, taskName,dateFrom, dateTo/*,courseId*/)){
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

    public List<Task> getTasksByCourseId(String courseId) throws DaoException {
        return executeQuery(GET_TASKS_BY_COURSE_ID,new TaskRowMapper(), courseId);
    }
}
