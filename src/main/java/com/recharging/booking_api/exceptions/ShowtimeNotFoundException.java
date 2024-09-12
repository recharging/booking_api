package com.recharging.booking_api.exceptions;

public class ShowtimeNotFoundException extends RuntimeException {
    public ShowtimeNotFoundException(Integer sid) {
        super("Could not find event time with id: " + sid + ".");
    }
}
