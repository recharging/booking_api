package com.recharging.booking_api.serviceImpls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.recharging.booking_api.entities.Booking;
import com.recharging.booking_api.entities.User;
import com.recharging.booking_api.entities.Showtime;
import com.recharging.booking_api.exceptions.BookingNotFoundException;
import com.recharging.booking_api.exceptions.UserNotFoundException;
import com.recharging.booking_api.exceptions.ShowtimeNotFoundException;
import com.recharging.booking_api.exceptions.SeatsNotEnoughException;
import com.recharging.booking_api.repositories.BookingRepository;
import com.recharging.booking_api.repositories.UserRepository;
import com.recharging.booking_api.repositories.ShowtimeRepository;
import com.recharging.booking_api.services.BookingService;
import com.recharging.booking_api.services.ShowtimeService;

@Primary
@Service
public class BookingWithLoggingServiceImpl implements BookingService {

  private final Logger logger = LoggerFactory.getLogger(BookingWithLoggingServiceImpl.class);

  private ShowtimeService showtimeService;
  private BookingRepository bookingRepository;
  private UserRepository userRepository;
  private ShowtimeRepository showtimeRepository;

  // @Autowired
  public BookingWithLoggingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository,
      ShowtimeRepository showtimeRepository, ShowtimeService showtimeService) {
    this.showtimeService = showtimeService;
    this.bookingRepository = bookingRepository;
    this.userRepository = userRepository;
    this.showtimeRepository = showtimeRepository;
  }

  @Override
  public Booking createBooking(Showtime showtime, User user, Integer bookedSeats) {
    // initialise booking with 0
    Booking newBooking = new Booking(showtime, user, bookedSeats);
    bookingRepository.save(newBooking);
    // update showtime with new number of seats
    showtimeService.changeBalSeats(showtime, newBooking, 2 * bookedSeats);
    return newBooking;
  }

  @Override
  public Booking findBookingByBid(Integer bid) {
    Booking booking = bookingRepository.findBookingByBid(bid);
    return booking;
  };

  @Override
  public ArrayList<Booking> getAllBookings() {
    List<Booking> allBookings = bookingRepository.findAll();
    return (ArrayList<Booking>) allBookings;
  }

  @Override
  public Booking updateBooking(int bid, Booking booking) {

    Booking bookingToUpdate = bookingRepository.findById(bid).orElseThrow(() -> new BookingNotFoundException(bid));

    int increasedSeats = booking.getBookedSeats() - bookingToUpdate.getBookedSeats();

    int initialSeats = bookingToUpdate.getBookedSeats();

    bookingToUpdate.setBookedSeats(booking.getBookedSeats());
    Showtime showtime = bookingToUpdate.getShowtime();

    try {

      updateBalanceSeats(showtime);
      System.out.println("Booking " + bookingToUpdate.getBid() + " has been updated");
      return bookingRepository.save(bookingToUpdate);
    } catch (SeatsNotEnoughException e) {

      logger
          .error("🔴 Not enought seats! You have increased seats from " + initialSeats
              + " to " + booking.getBookedSeats() + "(increase by " + increasedSeats + "). Seats available are only "
              + showtime.getBalSeats());
      throw new RuntimeException("Not enough seats available for booking update", e);
    }
  }

  @Override
  public void deleteBooking(Integer bid) {
    Booking bookingToDelete = bookingRepository.findById(bid).orElseThrow(() -> {
      return new BookingNotFoundException(bid);
    });

    // Get the showtime associated with the booking
    Showtime showtime = bookingToDelete.getShowtime();

    // Remove the booking from the showtime's bookings list
    showtime.getBooking().remove(bookingToDelete);
    logger.info("🟢 Booking of " + bookingToDelete.getBookedSeats() + " seats has been deleted");
    bookingRepository.deleteById(bid);

    try {
      updateBalanceSeats(showtime);
    } catch (SeatsNotEnoughException e) {

      e.printStackTrace();
    }
  }

  @Override
  // @Transactional
  public Booking addBooking(int uid, int sid, Booking booking) {
    User user = userRepository.findById(uid).orElseThrow(() -> new UserNotFoundException(uid));
    Showtime showtime = showtimeRepository.findById(sid).orElseThrow(() -> new ShowtimeNotFoundException(sid));

    try {

      // Check seat availability before saving the booking
      if (showtime.getBalSeats() < booking.getBookedSeats()) {
        throw new SeatsNotEnoughException(booking.getBookedSeats());
      }

      booking.setUser(user);
      booking.setShowtime(showtime);

      Booking savedBooking = bookingRepository.save(booking);
      updateBalanceSeats(showtime);
      return savedBooking;
    } catch (SeatsNotEnoughException e) {
      logger.error("🔴 Not enough seats! " + booking.getBookedSeats() + " seats are added. Seats available are only "
          + showtime.getBalSeats());
      throw new RuntimeException("Not enough seats available for booking update", e);
    }
  }

  private void updateBalanceSeats(Showtime showtime) throws SeatsNotEnoughException {
    List<Booking> bookings = showtime.getBooking();
    int totalSeats = showtime.getTotalSeats();
    int bookedSeats = bookings.stream().mapToInt(Booking::getBookedSeats).sum();
    if (bookedSeats > totalSeats) {
      throw new SeatsNotEnoughException(bookedSeats);
    }

    showtime.setBalSeats(totalSeats - bookedSeats);
    logger
        .info("🟢 The balance seats for " + showtime.getEvent().getDescription() + " on " + showtime.getDate()
            + " is now " + showtime.getBalSeats());
    showtimeRepository.save(showtime);
  }
}
