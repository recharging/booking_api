package com.recharging.booking_api.exceptions;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Integer bid) {
        super("Could not find booking with id: " + bid + ".");
    }
}

