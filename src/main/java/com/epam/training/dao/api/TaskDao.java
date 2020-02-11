package main.java.com.epam.training.dao.api;

import main.java.com.epam.training.entity.Task;
import main.java.com.epam.training.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface TaskDao extends Dao<Task> {

    void save(long courseId, String name, LocalDate dateFrom, LocalDate dateTo) throws DaoException;

    void updateById(long courseId, String name, LocalDate dateFrom, LocalDate dateTo, long taskId) throws DaoException;

    List<Task> findAllByCourseId(long courseId) throws DaoException;
}
