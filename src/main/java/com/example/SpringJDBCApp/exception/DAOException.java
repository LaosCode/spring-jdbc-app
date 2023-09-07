package com.example.SpringJDBCApp.exception;

public class DAOException
        extends RuntimeException {
    public DAOException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
