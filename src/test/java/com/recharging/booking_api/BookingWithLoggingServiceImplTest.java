package com.recharging.booking_api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.recharging.booking_api.entities.Booking;
import com.recharging.booking_api.entities.Event;
import com.recharging.booking_api.entities.User;
import com.recharging.booking_api.entities.Showtime;
import com.recharging.booking_api.exceptions.BookingNotFoundException;
import com.recharging.booking_api.exceptions.UserNotFoundException;
import com.recharging.booking_api.exceptions.ShowtimeNotFoundException;
import com.recharging.booking_api.exceptions.SeatsNotEnoughException;
import com.recharging.booking_api.repositories.BookingRepository;
import com.recharging.booking_api.repositories.UserRepository;
import com.recharging.booking_api.repositories.ShowtimeRepository;
import com.recharging.booking_api.serviceImpls.BookingWithLoggingServiceImpl;
import com.recharging.booking_api.services.ShowtimeService;

public class BookingWithLoggingServiceImplTest {

  @Mock
  private BookingRepository bookingRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private ShowtimeRepository showtimeRepository;

  @Mock
  private ShowtimeService showtimeService;

  @InjectMocks
  private BookingWithLoggingServiceImpl bookingWithLoggingServiceImpl;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateBooking_Valid() {
    //Setup
    Showtime showtime = new Showtime();
    User user = new User();
    Booking booking = new Booking(showtime, user, 2);
    //Mock
    when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
    //Execute
    Booking createdBooking = bookingWithLoggingServiceImpl.createBooking(showtime, user, 2);
    //Assert
    assertNotNull(createdBooking);
    assertEquals(2, createdBooking.getBookedSeats());
  }

  @Test
  public void testFindBookingByBid_Valid() {
    //Setup
    Booking booking = new Booking();
    //Mock
    when(bookingRepository.findBookingByBid(anyInt())).thenReturn(booking);
    // Execute
    Booking foundBooking = bookingWithLoggingServiceImpl.findBookingByBid(1);
    // Assert
    assertNotNull(foundBooking);
  }

  @Test
  public void testGetAllBookings() {
    // Setup
    List<Booking> bookings = new ArrayList<>();
    bookings.add(new Booking());
    //Mock
    when(bookingRepository.findAll()).thenReturn(bookings);
    //Execute
    List<Booking> allBookings = bookingWithLoggingServiceImpl.getAllBookings();
    //Assert
    assertEquals(1, allBookings.size());
  }

  @Test
  public void testUpdateBooking_Valid() {
      //Setup
      Showtime showtime = new Showtime();
      showtime.setBooking(new ArrayList<>()); // Initialize bookings list
      Event event = new Event();
      event.setDescription("Sample Event");
      showtime.setEvent(event);
      Booking booking = new Booking(showtime, new User(), 2);
      Booking bookingToUpdate = new Booking(showtime, new User(), 1);
      //Mock
      when(bookingRepository.findById(anyInt())).thenReturn(Optional.of(bookingToUpdate));
      when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
      //Execute
      Booking updatedBooking = bookingWithLoggingServiceImpl.updateBooking(1, booking);
      //Assert
      assertNotNull(updatedBooking);
      assertEquals(2, updatedBooking.getBookedSeats());
  }

  @Test
  public void testDeleteBooking_Valid() {
      //Setup
      Event event = new Event();
      event.setDescription("Sample Event");
      Showtime showtime = new Showtime();
      showtime.setEvent(event);
      showtime.setBooking(new ArrayList<>());
      Booking booking = new Booking();
      booking.setShowtime(showtime);
      //Mock
      when(bookingRepository.findById(anyInt())).thenReturn(Optional.of(booking));
      //Execute
      bookingWithLoggingServiceImpl.deleteBooking(1);
      //Verify
      verify(bookingRepository, times(1)).deleteById(1);
  }

  @Test
  public void testAddBooking_Valid() {
    //Setup
    User user = new User();
    Showtime showtime = new Showtime();
    Event event = new Event();
    event.setDescription("Sample Event");
    showtime.setEvent(event);
    showtime.setBooking(new ArrayList<>());
    Booking booking = new Booking();
    //Mock
    when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
    when(showtimeRepository.findById(anyInt())).thenReturn(Optional.of(showtime));
    when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
    //Execute
    Booking addedBooking = bookingWithLoggingServiceImpl.addBooking(1, 1, booking);
    //Assert
    assertNotNull(addedBooking);
  }
}
