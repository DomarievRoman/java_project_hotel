package com.domariev.controller.dao.exception;


@SuppressWarnings("serial")
public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message) {
        super(message);
    }
}

