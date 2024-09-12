package com.recharging.booking_api.services;

import java.util.ArrayList;

import com.recharging.booking_api.entities.Booking;
import com.recharging.booking_api.entities.Showtime;
import com.recharging.booking_api.entities.User;

public interface BookingService {

  // // CREATE
  Booking createBooking(Showtime showtime, User user, Integer bookedSeats);

  // // READ GET ONE
  Booking findBookingByBid(Integer bid);

  // READ GET ALL
  ArrayList<Booking> getAllBookings();

  // UPDATE
  Booking updateBooking(int bid, Booking booking);

  // DELETE
  void deleteBooking(Integer bid);

  Booking addBooking(int uid, int sid, Booking booking);

  // ArrayList<Booking> searchBooking(Integer bid);

}
