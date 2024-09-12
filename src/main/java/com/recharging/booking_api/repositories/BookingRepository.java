package com.recharging.booking_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recharging.booking_api.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingByBid(Integer bid);
}
