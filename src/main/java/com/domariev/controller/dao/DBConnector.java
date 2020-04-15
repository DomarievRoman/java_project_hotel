package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DAOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void main(String[] args) throws DAOException {
        DBConnector d = new DBTable();
    }

}
