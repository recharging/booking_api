package com.recharging.booking_api.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer uid) {
        super("Could not find user with id: " + uid + ".");
    }
}
