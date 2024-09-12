package com.recharging.booking_api.services;

import com.recharging.booking_api.entities.Booking;
import com.recharging.booking_api.entities.Showtime;

public interface ShowtimeService {
    
    Showtime findShowtimeBySid(Integer sid);

    Showtime changeBalSeats(Showtime showtime, Booking booking, int newBookedSeats);

}
