package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl extends DbConnector implements Dao<Room> {
    private static final String INSERT_INTO_ROOM = "INSERT INTO room(roomType, amountOfSleepingPlaces, floor, available, price, hotel) VALUES" +
            "(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_FROM_ROOM = "SELECT * FROM room";
    private static final String SELECT_ROOM_BY_ID = "SELECT id, roomType, amountOfSleepingPlaces, floor, available, price, hotel FROM room WHERE id = ?";
    private static final String SELECT_ROOM_BY_FOREIGN_KEY = "SELECT id, roomType, amountOfSleepingPlaces, floor, available, price, hotel FROM room WHERE hotel = ?";
    private static final String UPDATE_ROOM = "UPDATE room SET roomType = ?, amountOfSleepingPlaces = ?, floor = ?, available = ?, price=?, hotel = ? WHERE id = ?";
    private static final String DELETE_FROM_ROOM = "DELETE FROM room WHERE id = ?";

    private static final Logger log = LogManager.getLogger(RoomDaoImpl.class);

    public RoomDaoImpl() throws DaoException {
    }

    @Override
    public void add(Room room) throws DaoException {
        PreparedStatement prStatement = null;
        Connection connection = getConnection();
        try {
            prStatement = connection.prepareStatement(INSERT_INTO_ROOM);
            log.info("PreparedStatement get connection");
            prStatement.setString(1, room.getRoomType());
            prStatement.setInt(2, room.getAmountOfSleepingPlaces());
            prStatement.setInt(3, room.getFloor());
            prStatement.setBoolean(4, room.getAvailable());
            prStatement.setInt(5, room.getPrice());
            prStatement.setLong(6, room.getHotel());
            prStatement.executeUpdate();
            log.debug("Room " + "№" + room.getId() + " created");
        } catch (SQLException e) {
            log.error("Error during insert room's record into DB", e);
            throw new DaoException("Cannot insert record into room table", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
    }

    @Override
    public List<Room> getAll() throws DaoException {
        List<Room> roomList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet rSet = null;
        try {
            statement = connection.createStatement();
            log.info("Statement get connection");
            try {
                rSet = statement.executeQuery(SELECT_ALL_FROM_ROOM);
                log.info("Get ResultSet");
                while (rSet.next()) {
                    Room room = new Room();
                    room.setId(rSet.getInt("id"));
                    room.setRoomType(rSet.getString("roomType"));
                    room.setAmountOfSleepingPlaces(rSet.getInt("amountOfSleepingPlaces"));
                    room.setFloor(rSet.getInt("floor"));
                    room.setAvailable(rSet.getBoolean("available"));
                    room.setPrice(rSet.getInt("price"));
                    room.setHotel(rSet.getLong("hotel"));
                    roomList.add(room);
                    log.debug("Room " + "№" + room.getId() + " gotten");
                }
            } finally {
                closeResultSet(rSet);
            }
        } catch (SQLException e) {
            log.error("Error during get all room's records from DB", e);
            throw new DaoException("Cannot get all records from room hotel", e);
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
        return roomList;
    }

    @Override
    public Room getById(long id) throws DaoException {
        Room room = null;
        PreparedStatement prStatement = null;
        ResultSet rSet = null;
        Connection connection = getConnection();
        try {
            prStatement = connection.prepareStatement(SELECT_ROOM_BY_ID);
            log.info("prStatement get connection");
            prStatement.setLong(1, id);
            try {
                rSet = prStatement.executeQuery();
                while (rSet.next()) {
                    String roomType = rSet.getString("roomType");
                    int amountOfSleepingPlaces = rSet.getInt("amountOfSleepingPlaces");
                    int floor = rSet.getInt("floor");
                    boolean available = rSet.getBoolean("available");
                    int price = rSet.getInt("price");
                    long hotelId = rSet.getLong("hotel");
                    room = new Room(id, roomType, amountOfSleepingPlaces, floor, available, price, hotelId);
                    log.debug("Room " + "with id " + room.getId() + " gotten");
                }
            } finally {
                closeResultSet(rSet);
            }
        } catch (SQLException e) {
            log.error("Error during get room's record by ID from DB", e);
            throw new DaoException("Cannot get record by ID from table room", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return room;
    }

    public List<Room> getByForeignKey(long hotelId) throws DaoException {
        List<Room> hotelRooms = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet rSet = null;
        Connection connection = getConnection();
        try {
            prStatement = connection.prepareStatement(SELECT_ROOM_BY_FOREIGN_KEY);
            log.info("prStatement get connection");
            prStatement.setLong(1, hotelId);
            try {
                rSet = prStatement.executeQuery();
                while (rSet.next()) {
                    Room room = new Room();
                    room.setId(rSet.getInt("id"));
                    room.setRoomType(rSet.getString("roomType"));
                    room.setAmountOfSleepingPlaces(rSet.getInt("amountOfSleepingPlaces"));
                    room.setFloor(rSet.getInt("floor"));
                    room.setAvailable(rSet.getBoolean("available"));
                    room.setPrice(rSet.getInt("price"));
                    room.setHotel(rSet.getLong("hotel"));
                    hotelRooms.add(room);
                    log.debug("Room " + "from hotel №" + room.getHotel() + " gotten");
                }
            } finally {
                closeResultSet(rSet);
            }
        } catch (SQLException e) {
            log.error("Error during get room's record by foreign key from DB", e);
            throw new DaoException("Cannot get record by foreign key from table room", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return hotelRooms;
    }

    @Override
    public boolean update(Room room) throws DaoException {
        boolean updatedRow;
        Connection connection = getConnection();
        PreparedStatement prStatement = null;
        try {
            prStatement = connection.prepareStatement(UPDATE_ROOM);
            prStatement.setString(1, room.getRoomType());
            prStatement.setInt(2, room.getAmountOfSleepingPlaces());
            prStatement.setInt(3, room.getFloor());
            prStatement.setBoolean(4, room.getAvailable());
            prStatement.setInt(5, room.getPrice());
            prStatement.setLong(6, room.getHotel());
            prStatement.setLong(7, room.getId());
            updatedRow = prStatement.executeUpdate() > 0;
            log.info("Row " + "with id " + room.getId() + " updated");
        } catch (SQLException e) {
            log.error("Error during update room's table row", e);
            throw new DaoException("Cannot update row from table room", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return updatedRow;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        boolean deletedRow;
        Connection connection = getConnection();
        PreparedStatement prStatement = null;
        try {
            prStatement = connection.prepareStatement(DELETE_FROM_ROOM);
            prStatement.setLong(1, id);
            deletedRow = prStatement.executeUpdate() > 0;
            log.debug("Row with id " + id + " deleted");
        } catch (SQLException e) {
            log.error("Error during delete room's table row", e);
            throw new DaoException("Cannot delete row from table room", e);
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

    private void closeResultSet(ResultSet rSet) {
        if (rSet != null) {
            try {
                rSet.close();
                log.info("ResultSet closed");
            } catch (SQLException e) {
                log.error("Cannot close resultSet", e);
            }
        }
    }
}
