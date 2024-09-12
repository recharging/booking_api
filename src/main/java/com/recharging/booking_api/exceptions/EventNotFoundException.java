package com.recharging.booking_api.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Integer eid) {
        super("Could not find event with id: " + eid + ".");
    }
}
