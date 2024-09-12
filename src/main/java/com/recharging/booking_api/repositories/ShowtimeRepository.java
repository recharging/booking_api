package com.recharging.booking_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recharging.booking_api.entities.Showtime;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
        
        Showtime findShowtimeBySid(Integer sid);

}
