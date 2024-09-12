package com.recharging.booking_api.serviceImpls;

import org.springframework.stereotype.Service;

import com.recharging.booking_api.entities.Booking;
import com.recharging.booking_api.entities.Showtime;
import com.recharging.booking_api.repositories.ShowtimeRepository;
import com.recharging.booking_api.services.ShowtimeService;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {

    private ShowtimeRepository showtimeRepository;

    private ShowtimeServiceImpl(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @Override
    public Showtime findShowtimeBySid(Integer sid) {
        Showtime showtime = showtimeRepository.findShowtimeBySid(sid);
        return showtime;
    };

    @Override
    public Showtime changeBalSeats(Showtime showtime, Booking booking, int newBookedSeats) {
        System.out.println("Number of balance seats in showtime " + showtime.getSid() + ": " + showtime.getBalSeats());    
        System.out.println("Existing number of booked seats in booking: " + booking.getBid() + ": " + booking.getBookedSeats());    
        System.out.println("Number of seats in showtime " + showtime.getSid() + " will be changed from " + showtime.getBalSeats() + " to " + (showtime.getBalSeats() + booking.getBookedSeats() - newBookedSeats));    
        showtime.setBalSeats(showtime.getBalSeats() + booking.getBookedSeats() - newBookedSeats);
        return showtimeRepository.save(showtime);
    };
}
