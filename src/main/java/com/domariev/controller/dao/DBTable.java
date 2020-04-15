package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class DBTable extends DBConnector {
    private static final String HOTEL_TABLE = "CREATE TABLE hotel(id long NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(255), address varchar(255)," +
            "rating int, ownerName varchar(255))";
    private static final String EMPLOYEE_TABLE = "CREATE TABLE  employee(employeeId long NOT NULL AUTO_INCREMENT PRIMARY KEY, nameSurname varchar(255)," +
            "profession varchar(255), salary int, hotelId long, FOREIGN KEY(hotelId) REFERENCES hotel(id) ON DELETE CASCADE)";
    private static final String ROOM_TABLE = "CREATE TABLE  room(roomId long NOT NULL AUTO_INCREMENT PRIMARY KEY, roomType varchar(255), amountOfSleepingPlaces int, " +
            "floor int, isAvailable boolean, price int, hotelId long, FOREIGN KEY(hotelId) REFERENCES hotel(id) ON DELETE CASCADE)";

    private static final Logger log = LogManager.getLogger(DBTable.class);

    public DBTable() throws DAOException {
        initTables();
    }

    public void initTables() throws DAOException {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            log.info("Statement get connection");
            statement.addBatch(HOTEL_TABLE);
            statement.addBatch(EMPLOYEE_TABLE);
            statement.addBatch(ROOM_TABLE);
            statement.executeBatch();
            log.info("Tables created");
        } catch (SQLException e) {
            log.error("Error during tables create\n", e);
            throw new DAOException("Cannot create tables!", e);
        } finally {
            closeStatementConnection(statement, connection);
        }
        try {
            insertData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertData() throws IOException, DAOException {
        Connection connection = getConnection();
        Statement statement = null;
        String filename = "src\\main\\resources\\batchFile.txt";
        List<String> tablesData = Files.readAllLines(Paths.get(filename));
        try {
            statement = connection.createStatement();
            log.info("Statement get connection");
            for (String query : tablesData) {
                statement.addBatch(query);
            }
            statement.executeBatch();
            log.info("Inserted data in tables");
        } catch (SQLException e) {
            log.error("Error during insert data in tables", e);
            throw new DAOException("Cannot insert data in tables!", e);
        } finally {
            closeStatementConnection(statement, connection);
        }
    }

    private void closeStatementConnection(Statement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
                log.info("Statement closed");
            } catch (SQLException e) {
                log.error("Cannot close Statement", e);
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
