package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DaoException;

import java.util.List;

public interface Dao<T> {
    void add(T obj) throws DaoException;

    List<T> getAll() throws DaoException;
    T getById(long id) throws DaoException;

    boolean update(T obj) throws DaoException;

    boolean delete(long id) throws DaoException;
}
