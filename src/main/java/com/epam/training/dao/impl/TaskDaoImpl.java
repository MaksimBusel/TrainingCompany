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
    private static final String GET_TASKS_BY_COURSE_ID = "SELECT * FROM tasks WHERE course_id=?";
    private static final String SAVE_TASK = "INSERT INTO tasks (course_id, task_name, date_from, date_to) VALUES(?, ?, ?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM tasks WHERE task_id = ?";
    private static final String EDIT_TASK_BY_ID = "UPDATE tasks SET course_id=?, task_name=?, date_from=?, date_to=?," +
            " WHERE task_id =?";

    //not use
    private static final String FIND_BY_ID = "SELECT * FROM tasks WHERE task_id = ?";

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

    public void save(String courseId, String name, String dateFrom, String dateTo) throws DaoException {
        executeUpdate(SAVE_TASK, courseId, name, dateFrom, dateTo);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }

    public List<Task> getTasksByCourseId(String courseId) throws DaoException {
        return executeQuery(GET_TASKS_BY_COURSE_ID, new TaskRowMapper(), courseId);
    }

    public void updateById(String courseId, String name, String dateFrom, String dateTo, String taskId) throws DaoException {
        executeUpdate(EDIT_TASK_BY_ID,courseId, name, dateFrom, dateTo, taskId);
    }
}
