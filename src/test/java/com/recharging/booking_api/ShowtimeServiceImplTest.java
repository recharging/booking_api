package com.recharging.booking_api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.recharging.booking_api.entities.Booking;
import com.recharging.booking_api.entities.Showtime;
import com.recharging.booking_api.repositories.ShowtimeRepository;
import com.recharging.booking_api.serviceImpls.ShowtimeServiceImpl;

public class ShowtimeServiceImplTest {

    @Mock
    private ShowtimeRepository showtimeRepository;

    @InjectMocks
    private ShowtimeServiceImpl showtimeServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindShowtimeBySid_Valid() {
        //SETUP
        Showtime showtime = new Showtime();
        //MOCK
        when(showtimeRepository.findShowtimeBySid(anyInt())).thenReturn(showtime);
        //EXECUTE
        Showtime foundShowtime = showtimeServiceImpl.findShowtimeBySid(1);
        //ASSERT
        assertNotNull(foundShowtime);
    }

    @Test
    public void testChangeBalSeats_Valid() {
        //SETUP
        Showtime showtime = new Showtime();
        Booking booking = new Booking();
        booking.setBookedSeats(2);
        showtime.setBalSeats(10);
        //MOCK
        when(showtimeRepository.save(any(Showtime.class))).thenReturn(showtime);
        //EXECUTE
        Showtime updatedShowtime = showtimeServiceImpl.changeBalSeats(showtime, booking, 4);
        //ASSERT
        assertEquals(8, updatedShowtime.getBalSeats());
    }
}