package com.recharging.booking_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recharging.booking_api.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query to find all customers with a certain first name
    List<User> findByName(String name);
    User findOneByName(String name);

}
