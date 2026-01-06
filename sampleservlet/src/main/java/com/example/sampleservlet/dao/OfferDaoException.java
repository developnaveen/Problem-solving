package com.example.sampleservlet.dao;

public class OfferDaoException extends RuntimeException {

    public OfferDaoException(String message) {
        super(message);
    }

    public OfferDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
