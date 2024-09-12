package com.recharging.booking_api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.recharging.booking_api.controllers.BookingController;
import com.recharging.booking_api.entities.Booking;
import com.recharging.booking_api.services.BookingService;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookings() {
        //SETUP
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        //MOCK
        when(bookingService.getAllBookings()).thenReturn(bookings);
        //EXECUTE
        ResponseEntity<ArrayList<Booking>> response = bookingController.getAllBookings();
        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testDeleteBooking() {
        //MOCK
        doNothing().when(bookingService).deleteBooking(anyInt());
        //EXECUTE
        ResponseEntity<Booking> response = bookingController.deleteBooking(1);
        //ASSERT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        //VERIFY
        verify(bookingService, times(1)).deleteBooking(1);
    }

    @Test
    public void testCreateBooking() {
        //SETUP
        Booking booking = new Booking();
        //MOCK
        when(bookingService.addBooking(anyInt(), anyInt(), any(Booking.class))).thenReturn(booking);
        //EXECUTE
        ResponseEntity<Booking> response = bookingController.createBooking(1, 1, booking);
        //ASSERT
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}