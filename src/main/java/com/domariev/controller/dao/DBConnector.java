package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DAOException;

import com.domariev.model.enums.Table;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class DBConnector {
    public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    public static final String DB_USER = "";
    public static final String DB_PASSWORD = "";

    private static final Logger log = LogManager.getLogger(DBConnector.class);

    public DBConnector() {

    }

    public Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            log.info("Connection success");
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Connection is not success");
            throw new DAOException("Cannot get connection", e);
        }
        return connection;
    }

    public void createTables() throws DAOException, IOException {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            log.info("Statement get connection");
            statement.addBatch(Table.HOTEL.getSqlCommand());
            statement.addBatch(Table.EMPLOYEE.getSqlCommand());
            statement.addBatch(Table.ROOM.getSqlCommand());
            statement.executeBatch();
            log.info("Tables created");
        } catch (SQLException e) {
            log.error("Error during create tables", e);
            throw new DAOException("Cannot create tables", e);
        } finally {
            closeStatementConnection(statement, connection);
        }
        initTables();
    }

    public void initTables() throws DAOException, IOException {
        Connection connection = getConnection();
        Statement statement = null;
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("tables_data.sql");
        List<String> sqlLines = IOUtils.readLines(inputStream, "UTF-8");
        try {
            statement = connection.createStatement();
            log.info("Statement get connection");
            for (String query : sqlLines) {
                statement.addBatch(query);
            }
            statement.executeBatch();
            log.info("Data inserted into tables");
        } catch (SQLException e) {
            log.error("Error during tables initializing", e);
            throw new DAOException("Cannot initialize tables", e);
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
