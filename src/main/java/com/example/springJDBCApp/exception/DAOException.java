package com.example.springJDBCApp.exception;

public class DAOException
        extends RuntimeException {
    public DAOException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
