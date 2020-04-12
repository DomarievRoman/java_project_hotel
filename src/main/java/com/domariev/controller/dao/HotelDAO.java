package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Hotel;

import java.util.List;

public interface HotelDAO {
    void add(Hotel hotel) throws DAOException;

    List<Hotel> getAll() throws DAOException;

    List<Hotel> getById(long id) throws DAOException;

    void update(Hotel hotel);

    void delete(long id) throws DAOException;
}
