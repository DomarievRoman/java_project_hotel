package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DAOException;

import java.util.List;

public interface DAO<T> {
    void add(T obj) throws DAOException;

    List<T> getAll() throws DAOException;
    T getById(long id) throws DAOException;

    boolean update(T obj) throws DAOException;

    boolean delete(long id) throws DAOException;
}
