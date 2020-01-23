package com.epam.training.dao;

import com.epam.training.entity.Identifable;
import com.epam.training.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifable> {

    Optional<T> getById(Long id) throws DaoException;
    List<T> getAll()throws DaoException;
    void save(T item) throws DaoException;
    void removeById(Long id) throws DaoException;
}
