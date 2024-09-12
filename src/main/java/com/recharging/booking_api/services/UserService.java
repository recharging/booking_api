package com.recharging.booking_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.recharging.booking_api.entities.User;
import com.recharging.booking_api.entities.Booking;

public interface UserService {
    
    User createUser(User user);

    User getUser(Integer uid);

    ArrayList<User> getAllUsers();

    User updateUser(Integer uid, User user);

    void deleteUser(Integer uid);

    ArrayList<User> searchUser(String name);
    
    User findOneUser(String name);
    
}
