package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Hotel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAOImpl extends DBConnector implements HotelDAO {
    private static final String INSERT_INTO_HOTEL = "INSERT INTO hotel(name, address, rating, ownerName) VALUES" +
            "(?, ?, ?, ?)";
    private static final String SELECT_ALL_FROM_HOTELS = "SELECT * FROM hotel";
    private static final String SELECT_HOTEL_BY_ID = "SELECT id, name, address, rating, ownerName FROM hotel WHERE id = ?";
    private static final String UPDATE_HOTEL = "UPDATE hotel SET name = ?, address = ?, rating = ?, ownerName = ? WHERE id = ?";
    private static final String DELETE_FROM_HOTEL = "DELETE FROM hotel WHERE id = ?";

    private static final Logger log = LogManager.getLogger(HotelDAOImpl.class);

    public HotelDAOImpl() throws DAOException, IOException {
    }

    @Override
    public void add(Hotel hotel) throws DAOException {
        PreparedStatement prStatement = null;
        Connection connection = getConnection();
        try {
            prStatement = connection.prepareStatement(INSERT_INTO_HOTEL);
            log.info("PreparedStatement get connection");
            prStatement.setString(1, hotel.getName());
            prStatement.setString(2, hotel.getAddress());
            prStatement.setInt(3, hotel.getRating());
            prStatement.setString(4, hotel.getOwnerName());
            prStatement.executeUpdate();
            log.debug("Hotel " + "\"" + hotel.getName() + "\"" + " created");
        } catch (SQLException e) {
            log.error("Error during insert hotel's record into DB", e);
            throw new DAOException("Cannot insert record into hotel table", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
    }

    @Override
    public List<Hotel> getAll() throws DAOException {
        List<Hotel> hotelList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet rSet = null;
        try {
            statement = connection.createStatement();
            log.info("Statement get connection");
            try {
                rSet = statement.executeQuery(SELECT_ALL_FROM_HOTELS);
                log.info("Get ResultSet");
                while (rSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(rSet.getInt("id"));
                    hotel.setName(rSet.getString("name"));
                    hotel.setAddress(rSet.getString("address"));
                    hotel.setRating(rSet.getInt("rating"));
                    hotel.setOwnerName(rSet.getString("ownerName"));
                    hotelList.add(hotel);
                    log.debug("Hotel " + "\"" + hotel.getName() + "\"" + " gotten");
                }
            } finally {
                if (rSet != null) {
                    try {
                        rSet.close();
                        log.info("ResultSet closed");
                    } catch (SQLException e) {
                        log.error("Cannot close resultSet", e);
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Error during get all hotel's records from DB", e);
            throw new DAOException("Cannot get all records from table hotel", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    log.info("Statement closed");
                } catch (SQLException e) {
                    log.error("Cannot close statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    log.info("Connection closed");
                } catch (SQLException e) {
                    log.error("Cannot close connection", e);
                }
            }
        }
        return hotelList;
    }

    @Override
    public Hotel getById(long id) throws DAOException {
        Hotel hotel = null;
        PreparedStatement prStatement = null;
        ResultSet rSet = null;
        Connection connection = getConnection();
        try {
            prStatement = connection.prepareStatement(SELECT_HOTEL_BY_ID);
            log.info("prStatement get connection");
            prStatement.setLong(1, id);
            try {
                rSet = prStatement.executeQuery();
                while (rSet.next()) {
                    String name = rSet.getString("name");
                    String address = rSet.getString("address");
                    int rating = rSet.getInt("rating");
                    String ownerName = rSet.getString("ownerName");
                    hotel = new Hotel(id, name, address, rating, ownerName);
                    log.debug("Hotel " + "with id " + hotel.getId() + " gotten");
                }
            } finally {
                if (rSet != null) {
                    try {
                        rSet.close();
                        log.info("ResultSet closed");
                    } catch (SQLException e) {
                        log.error("Cannot close resultSet", e);
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Error during get hotel's record by ID from DB", e);
            throw new DAOException("Cannot get record by ID from table hotel", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return hotel;
    }

    @Override
    public boolean update(Hotel hotel) throws DAOException {
        boolean updatedRow;
        Connection connection = getConnection();
        PreparedStatement prStatement = null;
        try {
            prStatement = connection.prepareStatement(UPDATE_HOTEL);
            prStatement.setString(1, hotel.getName());
            prStatement.setString(2, hotel.getAddress());
            prStatement.setInt(3, hotel.getRating());
            prStatement.setString(4, hotel.getOwnerName());
            prStatement.setLong(5, hotel.getId());
            updatedRow = prStatement.executeUpdate() > 0;
            log.info("Row " + "with id " + hotel.getId() + " updated");
        } catch (SQLException e) {
            log.error("Error during update hotel's table row", e);
            throw new DAOException("Cannot update row from table hotel", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return updatedRow;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        boolean deletedRow;
        Connection connection = getConnection();
        PreparedStatement prStatement = null;
        try {
            prStatement = connection.prepareStatement(DELETE_FROM_HOTEL);
            prStatement.setLong(1, id);
            deletedRow = prStatement.executeUpdate() > 0;
            log.debug("Row with id " + id + " deleted");
        } catch (SQLException e) {
            log.error("Error during delete hotel's table row", e);
            throw new DAOException("Cannot delete row from table hotel", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return deletedRow;
    }

    private void closePrStatementConnection(PreparedStatement prStatement, Connection connection) {
        if (prStatement != null) {
            try {
                prStatement.close();
                log.info("PreparedStatement closed");
            } catch (SQLException e) {
                log.error("Cannot close statement", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
                log.info("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }
}
